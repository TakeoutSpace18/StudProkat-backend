package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.ErrorMessageDto;
import ru.studprokat.backend.dto.ValidationExceptionDto;
import ru.studprokat.backend.dto.wallet.CouponDto;
import ru.studprokat.backend.dto.wallet.CreateCouponDto;
import ru.studprokat.backend.dto.wallet.MoneyDto;
import ru.studprokat.backend.dto.wallet.TransactionDto;
import ru.studprokat.backend.service.WalletService;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/renting/", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "api.wallet.tag.name", description = "api.wallet.tag.description")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Operation(summary = "api.wallet.apply_coupon.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.wallet.apply_coupon.200.description"),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.wallet.coupon_already_used.forbidden.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PostMapping(value = "/users/{userId}/wallet")
    public ResponseEntity<MoneyDto> applyCoupon(@RequestBody @Valid CouponDto couponDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        return ResponseEntity.ok(this.walletService.applyCoupon(userId, couponDto));
    }

    @Operation(summary = "api.wallet.get_balance.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.wallet.get_balance.200.description"),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "/users/{userId}/wallet")
    public ResponseEntity<MoneyDto> getBalance(@PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        return ResponseEntity.ok(this.walletService.getBalance(userId));
    }

    @Operation(summary = "api.wallet.create_coupon.operation.summary", description = "api.only_for_admins.description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.wallet.create_coupon.200.description"),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PostMapping(value = "/coupons")
    public ResponseEntity<CreateCouponDto> createCoupon(@RequestBody MoneyDto moneyDto, Authentication auth) {
        PermissionChecker.checkAdminPermission(auth);
        return ResponseEntity.ok(this.walletService.createCoupon(moneyDto));
    }

    @Operation(summary = "api.wallet.transaction.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.wallet.transaction.200.description"),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PostMapping(value = "/users/{userId}/transaction")
    public ResponseEntity<MoneyDto> transaction(@RequestBody @Valid TransactionDto transactionDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatching(userId, auth);
        return ResponseEntity.ok(this.walletService.transaction(userId, transactionDto));
    }

    @Operation(summary = "api.wallet.set_balance.operation.summary", description = "api.only_for_admins.description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.wallet.set_balance.200.description"),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PatchMapping(value = "/users/{userId}/wallet")
    public ResponseEntity<MoneyDto> setBalance(@RequestBody @Valid MoneyDto moneyDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkAdminPermission(auth);
        return ResponseEntity.ok(this.walletService.setBalance(userId, moneyDto));
    }

}
