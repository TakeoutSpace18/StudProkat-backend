package ru.studprokat.backend.service;

import ru.studprokat.backend.dto.user.UserInputDto;
import ru.studprokat.backend.dto.user.UserLoginDto;
import ru.studprokat.backend.dto.user.UserOutputDto;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    UserOutputDto createRegularUser(UserInputDto userInputDto);
    void delete(UUID id);
    UserOutputDto alter(UUID id, UserInputDto userInputDto);
    UserOutputDto findById(UUID id);
    UserLoginDto findLoginDataByEmail(String email);
    List<UserOutputDto> list();

}
