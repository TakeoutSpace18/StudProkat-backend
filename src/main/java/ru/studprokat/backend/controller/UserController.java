package ru.studprokat.backend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.ErrorMessageDto;
import ru.studprokat.backend.dto.user.UserInputDto;
import ru.studprokat.backend.dto.user.UserOutputDto;
import ru.studprokat.backend.dto.ValidationExceptionDto;
import ru.studprokat.backend.service.UsersService;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// TODO: Path variable validation

@RestController
@RequestMapping(value = "renting/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "api.users.tag.name", description = "api.users.tag.description")
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "api.users.create.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.users.create.api-responses.200.description"),
            @ApiResponse(responseCode = "400", description = "api.validation.error.400.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ValidationExceptionDto.class)))}),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.users.create.email.already.registered.api-responses.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserOutputDto> create(@RequestBody @Valid UserInputDto userInputDto) {
        UserOutputDto userOutputDto = this.usersService.createRegularUser(userInputDto);
        return ResponseEntity.ok(userOutputDto);
    }

    @Operation(summary = "api.users.delete.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.users.delete.api-responses.200.description"),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "404", description = "api.user_not_found.error.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        this.usersService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "api.users.alter.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.users.alter.api-responses.200.description"),
            @ApiResponse(responseCode = "401", description = "api.unauthorized.error.401.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "403", description = "api.forbidden.error.403.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "404", description = "api.user_not_found.error.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })

    @Hidden
    @PatchMapping(value = "{userId}")
    public ResponseEntity<UserOutputDto> alter(@RequestBody UserInputDto userInputDto, @PathVariable UUID userId, Authentication auth) {
        PermissionChecker.checkIdMatchingOrAdminPermission(userId, auth);
        // TODO: read only altered fields
        return ResponseEntity.ok(this.usersService.alter(userId, userInputDto));
    }

    @Operation(summary = "api.users.list.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.users.list.api-responses.200.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = UserOutputDto.class)))}),
            @ApiResponse(responseCode = "404", description = "api.users_not_found.error.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping
    public ResponseEntity<List<UserOutputDto>> list() {
        return ResponseEntity.ok(this.usersService.list());
    }

    @Operation(summary = "api.users.get_by_id.operation.summary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "api.users.get_by_id.api-responses.200.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserOutputDto.class))}),
            @ApiResponse(responseCode = "404", description = "api.user_not_found.error.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))}),
            @ApiResponse(responseCode = "500", description = "api.server.error.500.description",
                    content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorMessageDto.class))})
    })
    @GetMapping(value = "{userId}")
    public ResponseEntity<UserOutputDto> getById(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.usersService.findById(userId));
    }

}

