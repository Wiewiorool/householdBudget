package com.example.householdBudget.service;

import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.UserTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserTableRepository userTableRepository;

    public UserService(@Autowired UserTableRepository userTableRepository) {
        this.userTableRepository = userTableRepository;
    }

    public UserTableEntity addNewUser(String userFirstName, String userLastName) {
        UserTableEntity newUser = UserTableEntity.builder()
                .name(userFirstName)
                .surname(userLastName)
                .build();
        return userTableRepository.save(newUser);
    }

    public UserTableEntity findByNameAndSurname(String userFirstName, String userLastName) {

        List<UserTableEntity> user = userTableRepository.findByNameAndSurname(userFirstName, userLastName);

        if (user.isEmpty()) {
            log.info("User not found");
        }
        if (user.size() > 1) {
            log.info("Found more than one user with that user name and last name ");
        }

        return user.getFirst();
    }

}
