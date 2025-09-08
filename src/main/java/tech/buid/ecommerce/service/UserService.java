package tech.buid.ecommerce.service;

import org.springframework.stereotype.Service;
import tech.buid.ecommerce.controller.dto.CreateUserDto;
import tech.buid.ecommerce.entities.BillingAddressEntity;
import tech.buid.ecommerce.entities.UserEntity;
import tech.buid.ecommerce.repository.BillingAddressRepository;
import tech.buid.ecommerce.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BillingAddressRepository billingAddressRepository;

    public UserService(UserRepository userRepository, BillingAddressRepository billingAddressRepository) {
        this.userRepository = userRepository;
        this.billingAddressRepository = billingAddressRepository;
    }

    public UserEntity createUser(CreateUserDto dto) {

        var billingAddress = new BillingAddressEntity();
        billingAddress.setAddress(dto.address());
        billingAddress.setNumber(dto.number());
        billingAddress.setComplement(dto.complement());

        billingAddressRepository.save(billingAddress);

        var user = new UserEntity();
        user.setFullName(dto.fullName());
        user.setBillingAddress(billingAddress);

        return userRepository.save(user);
    }

    public Optional<UserEntity> findById(UUID userId) {


        return userRepository.findById(userId);
    }
}
