package ru.studprokat.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.utils.Mocks;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(value = "renting/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto, Authentication authentication) {
        return ResponseEntity.ok(productService.create(productDto, authentication));
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> delete(@PathVariable UUID productId, Authentication auth) {
        ProductDto productDto = productService.findById(productId);
        PermissionChecker.checkIdMatchingOrAdminPermission(productDto.getUserId(), auth);
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "{productId}")
    public ResponseEntity<ProductDto> alter(@RequestBody ProductDto userDto, @PathVariable String productId) {

        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }
}