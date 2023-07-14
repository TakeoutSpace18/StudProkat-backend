package ru.studprokat.backend.service.cassandra;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.HistoryDto;
import ru.studprokat.backend.dto.HistoryInputDto;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.exception.ClientNotFoundException;
import ru.studprokat.backend.exception.HistoryNotFoundException;
import ru.studprokat.backend.exception.ProductNotFoundException;
import ru.studprokat.backend.exception.UserNotFoundException;
import ru.studprokat.backend.mappings.Mappings;
import ru.studprokat.backend.repository.cassandra.HistoryByUserRepository;
import ru.studprokat.backend.repository.cassandra.ProductsByIdRepository;
import ru.studprokat.backend.repository.cassandra.UsersByIdRepository;
import ru.studprokat.backend.repository.cassandra.entity.HistoryByUser;
import ru.studprokat.backend.repository.cassandra.entity.ProductsById;
import ru.studprokat.backend.repository.cassandra.entity.UsersById;
import ru.studprokat.backend.service.HistoryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("cassandraHistoryService")
public class HistoryServiceImpl implements HistoryService {
    private final ObjectIdGenerators.UUIDGenerator uuidGenerator;
    private final UsersByIdRepository usersByIdRepository;
    private final ProductsByIdRepository productsByIdRepository;
    private final HistoryByUserRepository historyByUserRepository;
    public HistoryServiceImpl(UsersByIdRepository usersByIdRepository, ProductsByIdRepository productsByIdRepository, HistoryByUserRepository historyByUserRepository) {
        this.usersByIdRepository = usersByIdRepository;
        this.productsByIdRepository = productsByIdRepository;
        this.historyByUserRepository = historyByUserRepository;

        this.uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
    }

    @Override
    public HistoryDto create(HistoryInputDto historyInputDto,  Authentication authentication) {
        Optional<UsersById> client = usersByIdRepository.findById(historyInputDto.getClientId());
        if(client.isEmpty()) throw new ClientNotFoundException();

        UserLoginDto user = (UserLoginDto) authentication.getDetails();
        if(user==null) throw new UserNotFoundException();

        Optional<ProductsById> product = productsByIdRepository.findById(historyInputDto.getProductId());
        if(product.isEmpty()) throw new ProductNotFoundException();

        UUID id = this.uuidGenerator.generateId(null);
        if(!product.get().getUserId().equals(user.getId()))
            throw new ProductNotFoundException();

        HistoryByUser historyByUser = new HistoryByUser();

        historyByUser.setActive(true);
        historyByUser.setId(id);
        historyByUser.setClientId(historyInputDto.getClientId());
        historyByUser.setClientName(client.get().getName());
        historyByUser.setClientMiddleName(client.get().getMiddleName());
        historyByUser.setClientSurname(client.get().getSurname());
        historyByUser.setStartDate(LocalDate.now());
        historyByUser.setEndDate(historyInputDto.getEndDate());
        historyByUser.setProductId(product.get().getId());
        historyByUser.setProductName(product.get().getProductName());
        historyByUser.setUserId(user.getId());

        this.historyByUserRepository.save(historyByUser);
        return Mappings.toHistoryDto(historyByUser);
    }

    @Override
    public HistoryByUser findById(boolean active, UUID id) {
        return historyByUserRepository.findByKey_ActiveAndKey_Id(active,id);
    }


    @Override
    public List<HistoryDto> list() {
        List<HistoryByUser> result = this.historyByUserRepository.findAll();
        if (result.isEmpty()){
            throw new ProductNotFoundException();
        }
        return result.stream().map(Mappings::toHistoryDto).toList();
    }

    @Override
    public HistoryDto close(UUID id) {
        HistoryByUser result = historyByUserRepository.findByKey_ActiveAndKey_Id(true,id);
        if(result == null)
            throw new HistoryNotFoundException();
        historyByUserRepository.delete(result);
        result.setActive(false);
        historyByUserRepository.save(result);
        return Mappings.toHistoryDto(result);
    }
}
