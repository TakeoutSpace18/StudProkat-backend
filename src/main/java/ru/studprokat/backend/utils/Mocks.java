package ru.studprokat.backend.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserOutputDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Mocks {
    public static UserOutputDto userDto1() {
        ObjectIdGenerators.UUIDGenerator gen = new ObjectIdGenerators.UUIDGenerator();

        return new UserOutputDto("Ivan",
                "Ivanov",
                "Ivanovich",
                "a;lskdjf",
                Set.of("gg.com"),
                "addr",
                gen.generateId(null),
                PermissionLevel.USER,
                LocalDate.now());

    }

    public static List<UserOutputDto> userDtoList() {
        return List.of(Mocks.userDto1(), Mocks.userDto1());
    }

}
