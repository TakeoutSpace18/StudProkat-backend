package ru.studprokat.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.UserInputDto;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.dto.UserOutputDto;
import ru.studprokat.backend.exception.ForbiddenException;
import ru.studprokat.backend.service.UsersService;
import ru.studprokat.backend.utils.PermissionLevel;

import java.util.List;
import java.util.UUID;

// TODO: Path variable validation

@RestController
@RequestMapping(value = "renting/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UserOutputDto> create(@RequestBody @Valid UserInputDto userInputDto) {
        UserOutputDto userOutputDto = this.usersService.create(userInputDto);
        return ResponseEntity.ok(userOutputDto);
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId, Authentication auth) {
        checkPermission(userId, auth);
        this.usersService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(value = "{userId}")
    public ResponseEntity<UserOutputDto> alter(@RequestBody UserInputDto userInputDto, @PathVariable UUID userId, Authentication auth) {
        checkPermission(userId, auth);
        // TODO: read only altered fields
        return ResponseEntity.ok(this.usersService.alter(userId, userInputDto));
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> list() {
        return ResponseEntity.ok(this.usersService.list());
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserOutputDto> getById(@PathVariable UUID userId) {
        return ResponseEntity.ok(this.usersService.findById(userId));
    }

    private static void checkPermission(UUID userId, Authentication auth) {
        UserLoginDto userLoginDto = (UserLoginDto) auth.getDetails();
        if (userLoginDto.getPermissionLevel() == PermissionLevel.USER && !userLoginDto.getId().equals(userId)) {
            throw new ForbiddenException();
        }
    }
}

