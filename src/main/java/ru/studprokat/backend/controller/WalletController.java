package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.wallet.CouponDto;
import ru.studprokat.backend.dto.wallet.CreateCouponDto;
import ru.studprokat.backend.dto.wallet.MoneyDto;
import ru.studprokat.backend.dto.wallet.TransactionDto;
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
    public ResponseEntity<MoneyDto> applyCoupon(@RequestBody @Valid CouponDto couponDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        return ResponseEntity.ok(this.walletService.applyCoupon(userId, couponDto));
    }
    @GetMapping(value = "/users/{userId}/wallet")
    public ResponseEntity<MoneyDto> getBalance(@PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        return ResponseEntity.ok(this.walletService.getBalance(userId));
    }
    @PostMapping(value = "/coupons")
    public ResponseEntity<CreateCouponDto> createCoupon(@RequestBody MoneyDto moneyDto, Authentication auth) {
        PermissionChecker.checkAdminPermission(auth);
        return ResponseEntity.ok(this.walletService.createCoupon(moneyDto));
    }
    @PostMapping(value = "/users/{userId}/transaction")
    public ResponseEntity<MoneyDto> transaction(@RequestBody @Valid TransactionDto transactionDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatching(userId, auth);
        return ResponseEntity.ok(this.walletService.transaction(userId, transactionDto));
    }
    @PatchMapping(value = "/users/{userId}/wallet")
    public ResponseEntity<MoneyDto> setBalance(@RequestBody @Valid MoneyDto moneyDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkAdminPermission(auth);
        return ResponseEntity.ok(this.walletService.setBalance(userId, moneyDto));
    }

}
