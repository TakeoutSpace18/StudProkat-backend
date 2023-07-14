package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.ErrorMessageDto;
import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.HistoryInputDto;
import ru.studprokat.backend.dto.ValidationExceptionDto;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;
import ru.studprokat.backend.service.HistoryService;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.service.UsersService;
import ru.studprokat.backend.utils.AdvertisementStatus;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "renting", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.history.tag.name")
public class HistoryController {

    private final UsersService usersService;
    private final ProductService productService;
    private final HistoryService historyService;
    @Autowired
    public HistoryController(UsersService usersService, ProductService productService, HistoryService historyService) {
        this.usersService = usersService;
        this.productService = productService;
        this.historyService = historyService;
    }

    @Operation(summary = "api.history.create.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.history.create.200.description"),
            @ApiResponse(responseCode = "404", description = "api.history_create_not_found.description",
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
    @PostMapping(value = "users/{userId}/active_rent", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoryDto> create(@RequestBody @Valid HistoryInputDto historyInputDto,Authentication authentication) {
        productService.changeStatus(historyInputDto.getProductId(), AdvertisementStatus.BUSY);
        return ResponseEntity.ok(historyService.create(historyInputDto,authentication));
    }

    @Operation(summary = "api.history.list.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.history.list.200.description"),
            @ApiResponse(responseCode = "404", description = "api.history_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "users/{user_id}/history")
    public ResponseEntity<List<HistoryDto>> list(){
        return ResponseEntity.ok(historyService.list());
    }

    @Operation(summary = "api.history.close.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.history.close.200.description"),
            @ApiResponse(responseCode = "404", description = "api.history_not_found.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PostMapping(value = "history/{history_id}/close")
    public ResponseEntity<HistoryDto> close(@PathVariable UUID history_id, Authentication authentication){
        HistoryByUser historyByUser = historyService.findById(true, history_id);
        PermissionChecker.checkIdMatchingOrAdminPermission(historyByUser.getUserId(), authentication);
        productService.changeStatus(historyByUser.getProductId(),AdvertisementStatus.FREE);
        return ResponseEntity.ok(historyService.close(history_id));
    }
}
