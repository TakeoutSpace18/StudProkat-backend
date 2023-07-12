package ru.studprokat.backend.service;

import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.wallet.CouponDto;
import ru.studprokat.backend.dto.wallet.CreateCouponDto;
import ru.studprokat.backend.dto.wallet.MoneyDto;
import ru.studprokat.backend.dto.wallet.TransactionDto;

import java.util.UUID;

@Service
public interface WalletService {
    void createWallet(UUID userId, int initialBalance);
    MoneyDto getBalance(UUID userId);
    MoneyDto setBalance(UUID userId, MoneyDto moneyDto);
    MoneyDto applyCoupon(UUID userId, CouponDto coupon);
    CreateCouponDto createCoupon(MoneyDto money);
    MoneyDto transaction(UUID userId, TransactionDto transactionDto);
}
