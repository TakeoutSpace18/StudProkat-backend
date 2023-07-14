package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.ErrorMessageDto;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.ValidationExceptionDto;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.utils.AdvertisementType;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Validated
@RequestMapping(value = "renting/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "api.products.tag.name", description = "api.products.tag.description")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "api.products.create.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.create.200.description"),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto, Authentication authentication) {
        return ResponseEntity.ok(productService.create(productDto, authentication));
    }

    @Operation(summary = "api.products.delete.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.delete.200.description"),
            @ApiResponse(responseCode = "404", description = "api.product_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Void> delete(@PathVariable UUID productId, Authentication auth) {
        ProductDto productDto = productService.findById(productId);
        PermissionChecker.checkIdMatchingOrAdminPermission(productDto.getUserId(), auth);
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @Hidden
    @Operation(summary = "api.products.alter.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.alter.200.description"),
            @ApiResponse(responseCode = "404", description = "api.product_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PatchMapping(value = "{productId}")
    public ResponseEntity<ProductDto> alter(@RequestBody ProductDto productDto, @PathVariable String productId) {

        return ResponseEntity.ok(productDto);
    }

    @Operation(summary = "api.products.list.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.list.200.description"),
            @ApiResponse(responseCode = "404", description = "api.products_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @Operation(summary = "api.products.get_by_id.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.get_by_id.200.description"),
            @ApiResponse(responseCode = "404", description = "api.product_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "{productId}")
    public ResponseEntity<ProductDto> getById(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @Operation(summary = "api.products.get_by_user_id.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.get_by_user_id.200.description"),
            @ApiResponse(responseCode = "404", description = "api.products_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "{userId}/all")
    public ResponseEntity<List<ProductDto>> getByUserId(@PathVariable UUID userId){
        return ResponseEntity.ok(productService.findByUserId(userId));
    }

    @Operation(summary = "api.products.get_product_types.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.get_product_types.200.description"),
            @ApiResponse(responseCode = "404", description = "api.product_types_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "product_types")
    public ResponseEntity<Set<String>> getProductTypes(){
        return ResponseEntity.ok(productService.getProductTypes());
    }

    @Operation(summary = "api.products.get_by_product_type.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.get_by_product_type.200.description"),
            @ApiResponse(responseCode = "404", description = "api.products_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "product_types/**")
    public ResponseEntity<List<ProductDto>> getByProductType(HttpServletRequest request){
        return ResponseEntity.ok(productService.findByProductType(request.getRequestURI()
                .split(request.getContextPath() + "/product_types/")[1]));
    }

    @Operation(summary = "api.products.get_by_ad_type.operation.summary", description = "api.products.get_by_ad_type.operation.description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.products.get_by_ad_type.200.description"),
            @ApiResponse(responseCode = "404", description = "api.products_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "ad_types/{ad_type}")
    public ResponseEntity<List<ProductDto>> getByProductAd(@PathVariable AdvertisementType  ad_type){
        return ResponseEntity.ok(productService.findByAdType(ad_type));
    }
}