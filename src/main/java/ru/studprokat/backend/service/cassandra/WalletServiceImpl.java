package ru.studprokat.backend.service.cassandra;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.wallet.CouponDto;
import ru.studprokat.backend.dto.wallet.CreateCouponDto;
import ru.studprokat.backend.dto.wallet.MoneyDto;
import ru.studprokat.backend.dto.wallet.TransactionDto;
import ru.studprokat.backend.exception.*;
import ru.studprokat.backend.repository.cassandra.CouponRepository;
import ru.studprokat.backend.repository.cassandra.WalletRepository;
import ru.studprokat.backend.repository.cassandra.entity.Coupon;
import ru.studprokat.backend.repository.cassandra.entity.Wallet;
import ru.studprokat.backend.service.WalletService;

import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    private static final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);
    private final WalletRepository walletRepository;
    private final CouponRepository couponRepository;
    private final ObjectIdGenerators.UUIDGenerator uuidGenerator;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, CouponRepository couponRepository) {
        this.walletRepository = walletRepository;
        this.couponRepository = couponRepository;
        this.uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
    }

    @Override
    public void createWallet(UUID userId, int initialBalance) {
        Optional<Wallet> foundWallet = this.walletRepository.findById(userId);
        if (foundWallet.isPresent()) {
            String msg = String.format("Failed to create wallet for user %s: already exists", userId);
            log.error(msg);
            throw new InternalServerError(msg);
        }

        Wallet wallet = new Wallet().setUserId(userId).setMoney(initialBalance);
        this.walletRepository.save(wallet);
        log.debug("Wallet created for user {}", userId);
    }

    @Override
    public MoneyDto getBalance(UUID userId) {
        Optional<Wallet> foundWallet = this.walletRepository.findById(userId);
        if (foundWallet.isEmpty()) {
            log.debug("Failed to get wallet balance for user {}: not found", userId);
            throw new UserNotFoundException();
        }

        return new MoneyDto(foundWallet.get().getMoney());
    }

    @Override
    public MoneyDto setBalance(UUID userId, MoneyDto moneyDto) {
        Optional<Wallet> foundWallet = this.walletRepository.findById(userId);
        if (foundWallet.isEmpty()) {
            log.debug("Failed to set wallet balance for user {}: not found", userId);
            throw new UserNotFoundException();
        }
        foundWallet.get().setMoney(moneyDto.getMoney());
        this.walletRepository.save(foundWallet.get());
        log.debug("Set balance {} RUB to {} wallet", moneyDto.getMoney(), userId);
        return moneyDto;
    }

    @Override
    public MoneyDto applyCoupon(UUID userId, CouponDto couponDto) {
        Optional<Wallet> foundWallet = this.walletRepository.findById(userId);
        if (foundWallet.isEmpty()) {
            log.debug("Failed to apply coupon {} to user {}: wallet not found", couponDto.getId(), userId);
            throw new UserNotFoundException();
        }

        Optional<Coupon> foundCoupon = this.couponRepository.findById(couponDto.getId());
        if (foundCoupon.isEmpty()) {
            log.debug("Failed to apply coupon {} to user {}: coupon not cound", couponDto.getId(), userId);
            throw new CouponNotFoundException();
        }

        if (foundCoupon.get().isUsed()) {
            log.debug("Failed to apply coupon {} to user {}: coupon already used", couponDto.getId(), userId);
            throw new CouponAlreadyUsedException();
        }

        foundWallet.get().setMoney(foundWallet.get().getMoney() + foundCoupon.get().getMoney());
        foundCoupon.get().setUsed(true);
        this.couponRepository.save((foundCoupon.get()));
        this.walletRepository.save(foundWallet.get());
        log.debug("Applied coupon {} of {} RUB to user {}", couponDto.getId(), foundCoupon.get().getMoney(),  userId);
        return new MoneyDto(foundWallet.get().getMoney());
    }

    @Override
    public CreateCouponDto createCoupon(MoneyDto moneyDto) {
        UUID id = this.uuidGenerator.generateId(null);
        Coupon coupon = new Coupon().setId(id).setUsed(false).setMoney(moneyDto.getMoney());
        this.couponRepository.save(coupon);
        log.info("Created coupon {} of {} RUB", id, moneyDto.getMoney());
        return new CreateCouponDto(id, coupon.getMoney());
    }

    @Override
    public MoneyDto transaction(UUID userId, TransactionDto transactionDto) {
        Optional<Wallet> foundUserWallet = this.walletRepository.findById(userId);
        if (foundUserWallet.isEmpty()) {
            log.error("Transaction failed: user's {} wallet not found", userId);
            throw new UserNotFoundException();
        }

        Optional<Wallet> foundClientWallet = this.walletRepository.findById(transactionDto.getClientId());
        if (foundClientWallet.isEmpty()) {
            log.error("Transaction failed: client't {} wallet not found", userId);
            throw new ClientNotFoundException();
        }
        foundUserWallet.get().subtractMoney(transactionDto.getMoney());
        foundClientWallet.get().addMoney(transactionDto.getMoney());

        this.walletRepository.save(foundClientWallet.get());
        this.walletRepository.save(foundUserWallet.get());
        log.debug("Transaction: user {} to client {}: {} RUB", userId, transactionDto.getClientId(), transactionDto.getMoney());

        return new MoneyDto(foundUserWallet.get().getMoney());
    }
}
