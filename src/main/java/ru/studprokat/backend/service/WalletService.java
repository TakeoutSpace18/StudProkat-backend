package ru.studprokat.backend.service;

import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.CouponDto;
import ru.studprokat.backend.dto.CreateCouponDto;
import ru.studprokat.backend.dto.MoneyDto;

import java.util.UUID;

@Service
public interface WalletService {
    void createWallet(UUID userId, int initialBalance);
    void deleteWallet(UUID userId);
    MoneyDto getBalance(UUID userId);
    MoneyDto applyCoupon(UUID userId, CouponDto coupon);
    CreateCouponDto createCoupon(MoneyDto money);

    

}
