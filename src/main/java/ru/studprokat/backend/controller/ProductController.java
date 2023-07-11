package ru.studprokat.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.utils.Mocks;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "renting/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<String> delete(@PathVariable String productId) {
        return ResponseEntity.ok(productId);
    }

    @PatchMapping(value = "{productId}")
    public ResponseEntity<ProductDto> alter(@RequestBody ProductDto userDto, @PathVariable String productId) {

        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> list() {
        return ResponseEntity.ok(List.of(Mocks.productDto(), Mocks.productDto()));
    }

    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable String productId) {
        return ResponseEntity.ok(Mocks.productDto());
    }
}