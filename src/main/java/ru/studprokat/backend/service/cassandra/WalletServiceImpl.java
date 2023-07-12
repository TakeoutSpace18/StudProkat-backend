package ru.studprokat.backend.service.cassandra;

import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.CouponDto;
import ru.studprokat.backend.dto.CreateCouponDto;
import ru.studprokat.backend.dto.MoneyDto;
import ru.studprokat.backend.service.WalletService;

import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    @Override
    public void createWallet(UUID userId, int initialBalance) {

    }

    @Override
    public void deleteWallet(UUID userId) {

    }

    @Override
    public MoneyDto getBalance(UUID userId) {
        return null;
    }

    @Override
    public MoneyDto applyCoupon(UUID userId, CouponDto coupon) {
        return null;
    }

    @Override
    public CreateCouponDto createCoupon(MoneyDto money) {
        return null;
    }
}
