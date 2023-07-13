package ru.studprokat.backend.service.cassandra;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.studprokat.backend.dto.ProductDto;
import ru.studprokat.backend.dto.UserInputDto;
import ru.studprokat.backend.dto.UserLoginDto;
import ru.studprokat.backend.dto.UserOutputDto;
import ru.studprokat.backend.exception.EmailAlreadyRegisteredException;
import ru.studprokat.backend.exception.UserNotFoundException;
import ru.studprokat.backend.exception.UsersNotFoundException;
import ru.studprokat.backend.mappings.Mappings;
import ru.studprokat.backend.repository.cassandra.UsersByEmailRepository;
import ru.studprokat.backend.repository.cassandra.UsersByIdRepository;
import ru.studprokat.backend.repository.cassandra.WalletRepository;
import ru.studprokat.backend.repository.cassandra.entity.UsersByEmail;
import ru.studprokat.backend.repository.cassandra.entity.UsersById;
import ru.studprokat.backend.repository.cassandra.entity.Wallet;
import ru.studprokat.backend.service.ProductService;
import ru.studprokat.backend.service.UsersService;
import ru.studprokat.backend.utils.PermissionLevel;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("cassandraUsersService")
public class UsersServiceImpl implements UsersService {
    private final UsersByIdRepository usersByIdRepository;
    private final UsersByEmailRepository usersByEmailRepository;
    private final WalletRepository walletRepository;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectIdGenerators.UUIDGenerator uuidGenerator;

    @Autowired
    public UsersServiceImpl(UsersByIdRepository usersByIdRepository, UsersByEmailRepository usersByEmailRepository, WalletRepository walletRepository, ProductService productService, PasswordEncoder passwordEncoder) {
        this.usersByEmailRepository = usersByEmailRepository;
        this.walletRepository = walletRepository;
        this.usersByIdRepository = usersByIdRepository;

        this.productService = productService;

        this.uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void createAdmin() {
        String adminEmail = "admin@admin.com";
        String adminPassword = "admin";

        Optional<UsersByEmail> foundAdmin = this.usersByEmailRepository.findById(adminEmail);
        if (foundAdmin.isEmpty()) {
            UserInputDto adminDto = new UserInputDto("admin",
                    "admin",
                    "admin",
                    adminEmail,
                    new HashSet<>(),
                    adminPassword);
            createUser(adminDto, PermissionLevel.ADMIN);
        }
    }

    @Override
    public UserOutputDto createRegularUser(UserInputDto userInputDto) {
        checkIfEmailAlreadyExistsInDatabase(userInputDto.getEmail());

        return createUser(userInputDto, PermissionLevel.USER);
    }

    private UserOutputDto createUser(UserInputDto userInputDto, PermissionLevel permissionLevel) {
        UUID id = this.uuidGenerator.generateId(null);
        String encodedPassword = this.passwordEncoder.encode(userInputDto.getPassword());
        LocalDate registrationDate = LocalDate.now();

        UsersByEmail usersByEmail = new UsersByEmail()
                .setId(id)
                .setEmail(userInputDto.getEmail())
                .setPassword(encodedPassword)
                .setRegistrationDate(registrationDate)
                .setPermissionLevel(permissionLevel);

        UsersById usersById = Mappings.toUsersById(userInputDto)
                .setId(id)
                .setRegistrationDate(registrationDate);

        Wallet wallet = new Wallet().setUserId(id).setMoney(0);

        this.walletRepository.save(wallet);
        this.usersByIdRepository.save(usersById);
        this.usersByEmailRepository.save(usersByEmail);
        return this.findById(id);
    }

    private void checkIfEmailAlreadyExistsInDatabase(String email) {
        Optional<UsersByEmail> usersByEmail = this.usersByEmailRepository.findById(email);
        if (usersByEmail.isPresent()) {
            throw new EmailAlreadyRegisteredException();
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<UsersById> usersById = this.usersByIdRepository.findById(id);
        if (usersById.isEmpty()) {
            throw new UserNotFoundException();
        }

        // delete all products owned by user
        List<ProductDto> products = this.productService.findByUserId(id);
        for (ProductDto productDto : products) {
            this.productService.delete(productDto.getId());
        }

        this.usersByEmailRepository.deleteById(usersById.get().getEmail());
        this.usersByIdRepository.deleteById(id);
        this.walletRepository.deleteById(id);
    }

    @Override
    public UserOutputDto alter(UUID id, UserInputDto userInputDto) {
        Optional<UsersById> oldUsersById = this.usersByIdRepository.findById(id);
        if (oldUsersById.isEmpty()) {
            throw new UserNotFoundException();
        }

        // TODO: double email check and id_by_email update
        UsersById newUsersById = Mappings.toUsersById(userInputDto);
        newUsersById.setRegistrationDate(oldUsersById.get().getRegistrationDate());
        newUsersById.setId(id);

        this.usersByIdRepository.save(newUsersById);
        return this.findById(id);
    }

    @Override
    public UserOutputDto findById(UUID id) {
        Optional<UsersById> entity = usersByIdRepository.findById(id);
        if (entity.isEmpty()) {
            throw new UserNotFoundException();
        }

        return Mappings.toUserOutputDto(entity.get());
    }

    @Override
    public UserLoginDto findLoginDataByEmail(String email) {
        Optional<UsersByEmail> usersByEmail = this.usersByEmailRepository.findById(email);
        if (usersByEmail.isEmpty()) {
            throw new UserNotFoundException();
        }

        // TODO: create mapper
        return new UserLoginDto(usersByEmail.get().getId(),
                usersByEmail.get().getEmail(),
                usersByEmail.get().getPassword(),
                usersByEmail.get().getPermissionLevel(),
                usersByEmail.get().getRegistrationDate());
    }

    @Override
    public List<UserOutputDto> list() {
        List<UsersById> allEntities = this.usersByIdRepository.findAll();
        if (allEntities.isEmpty()) {
            throw new UsersNotFoundException();
        }

        return allEntities.stream().map(Mappings::toUserOutputDto).toList();
    }
}
