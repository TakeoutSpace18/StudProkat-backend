package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.studprokat.backend.dto.CouponDto;
import ru.studprokat.backend.dto.CreateCouponDto;
import ru.studprokat.backend.dto.MoneyDto;
import ru.studprokat.backend.service.WalletService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/renting/", produces = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping(value = "/users/{userId}/wallet")
    public ResponseEntity<MoneyDto> applyCoupon(@RequestBody CouponDto couponDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        return ResponseEntity.ok(this.walletService.applyCoupon(userId, couponDto));
    }

    @PostMapping(value = "/coupons")
    public ResponseEntity<CreateCouponDto> createCoupon(@RequestBody MoneyDto moneyDto, Authentication auth) {
        PermissionChecker.checkAdminPermission(auth);
        return ResponseEntity.ok(this.walletService.createCoupon(moneyDto));
    }


}
