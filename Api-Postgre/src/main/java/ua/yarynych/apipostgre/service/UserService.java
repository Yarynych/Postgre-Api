package ua.yarynych.apipostgre.service;

import org.springframework.stereotype.Service;
import ua.yarynych.apipostgre.entity.User;
import ua.yarynych.apipostgre.repository.UserRepositoryDataSource1;
import ua.yarynych.apipostgre.repository.UserRepositoryDataSource2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private final UserRepositoryDataSource1 userRepositoryDataSource1;
    private final UserRepositoryDataSource2 userRepositoryDataSource2;

    public UserService(UserRepositoryDataSource1 userRepositoryDataSource1, UserRepositoryDataSource2 userRepositoryDataSource2) {
        this.userRepositoryDataSource1 = userRepositoryDataSource1;
        this.userRepositoryDataSource2 = userRepositoryDataSource2;
    }

    public List<User> getAllUsers() {
        List<User> users1 = userRepositoryDataSource1.findAll();
        List<User> users2 = userRepositoryDataSource2.findAll();
        return Stream.concat(users1.stream(), users2.stream()).collect(Collectors.toList());
    }

    public List<User> getUsersWithFilters(String username, String name, String surname) {
        List<User> users1 = userRepositoryDataSource1.findUsersWithFilters(username, name, surname);
        List<User> users2 = userRepositoryDataSource2.findUsersWithFilters(username, name, surname);
        return Stream.concat(users1.stream(), users2.stream()).collect(Collectors.toList());
    }
}
