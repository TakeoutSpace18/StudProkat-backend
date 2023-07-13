package ru.studprokat.backend.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.HistoryInputDto;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;
import ru.studprokat.backend.repository.cassandra.entity.ProductsById;
import ru.studprokat.backend.service.HistoryService;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.service.UsersService;
import ru.studprokat.backend.utils.AdvertisementStatus;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "renting", produces = APPLICATION_JSON_VALUE)
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

    @PostMapping(value = "users/{userId}/active_rent", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HistoryDto> create(@RequestBody @Valid HistoryInputDto historyInputDto,Authentication authentication) {
        productService.changeStatus(historyInputDto.getProductId(), AdvertisementStatus.BUSY);
        return ResponseEntity.ok(historyService.create(historyInputDto,authentication));
    }

    @GetMapping(value = "users/{user_id}/history")
    public ResponseEntity<List<HistoryDto>> list(){
        return ResponseEntity.ok(historyService.list());
    }
    @PostMapping(value = "history/{history_id}/close")
    public ResponseEntity<HistoryDto> close(@PathVariable UUID history_id, Authentication authentication){
        HistoryByUser historyByUser = historyService.findById(true, history_id);
        PermissionChecker.checkIdMatchingOrAdminPermission(historyByUser.getUserId(), authentication);
        productService.changeStatus(historyByUser.getProductId(),AdvertisementStatus.FREE);
        return ResponseEntity.ok(historyService.close(history_id));
    }
}
