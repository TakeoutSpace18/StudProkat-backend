package ru.studprokat.backend.service;

import org.springframework.security.core.Authentication;
import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.HistoryInputDto;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserBaseDto;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    HistoryDto create(HistoryInputDto historyInputDto, Authentication authentication);
    HistoryByUser findById(boolean active,UUID id);
    List<HistoryDto> list();
    HistoryDto close(UUID id);
}
