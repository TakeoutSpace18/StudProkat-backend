package ru.studprokat.backend.service;

import org.springframework.security.core.Authentication;
import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.HistoryInputDto;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserBaseDto;

import java.util.UUID;

public interface HistoryService {
    HistoryDto create(HistoryInputDto historyInputDto, Authentication authentication);
}
