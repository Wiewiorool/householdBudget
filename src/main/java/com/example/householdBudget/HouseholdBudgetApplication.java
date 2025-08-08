package com.example.householdBudget;

import com.example.householdBudget.database.entities.UserTableEntity;
import com.example.householdBudget.repositories.UserTableRepository;
import com.example.householdBudget.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class HouseholdBudgetApplication {

    private static final Logger log = LoggerFactory.getLogger(HouseholdBudgetApplication.class);

    public static void main(String[] args) {
        String firstName = "Adam";
        String lastName = "Smith";

        ConfigurableApplicationContext context = SpringApplication.run(HouseholdBudgetApplication.class, args);
        UserTableRepository userTableRepository = context.getBean(UserTableRepository.class);

        UserService userService = context.getBean(UserService.class);
        List<UserTableEntity> findAllUsers = userService.findByNameAndSurname(firstName, lastName);

        System.out.println(findAllUsers);
    }

}
