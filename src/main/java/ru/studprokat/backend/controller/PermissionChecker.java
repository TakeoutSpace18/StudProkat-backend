package ru.studprokat.backend.controller;

import org.springframework.security.core.Authentication;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.exception.ForbiddenException;
import ru.studprokat.backend.utils.PermissionLevel;

import java.util.UUID;

public class PermissionChecker {
    public static void checkIdMatchingOrAdminPermission(UUID targetId, Authentication auth) {
        UserLoginDto userLoginDto = (UserLoginDto) auth.getDetails();
        if (userLoginDto.getPermissionLevel() == PermissionLevel.USER && !userLoginDto.getId().equals(targetId)) {
            throw new ForbiddenException();
        }
    }

    public static void checkAdminPermission(Authentication auth) {
        UserLoginDto userLoginDto = (UserLoginDto) auth.getDetails();
        if (userLoginDto.getPermissionLevel() != PermissionLevel.ADMIN) {
            throw new ForbiddenException();
        }
    }
}
