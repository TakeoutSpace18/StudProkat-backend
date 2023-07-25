package ru.studprokat.backend.service;

import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.HistoryInputDto;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;

import java.util.List;
import java.util.UUID;

public interface HistoryService {
    HistoryDto create(UUID userId, HistoryInputDto historyInputDto);
    HistoryByUser findById(boolean active,UUID id);
    List<HistoryDto> findByUserId(UUID userId);
    HistoryDto close(UUID id);
}
