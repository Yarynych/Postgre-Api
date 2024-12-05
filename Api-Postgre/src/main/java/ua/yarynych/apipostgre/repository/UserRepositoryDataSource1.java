package ua.yarynych.apipostgre.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.yarynych.apipostgre.entity.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepositoryDataSource1 implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryDataSource1(@Qualifier("dataSource1") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> findAll() {
        String sql = "SELECT user_id AS id, login AS username, first_name AS name, last_name AS surname FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<User> findUsersWithFilters(String username, String name, String surname) {
        StringBuilder sql = new StringBuilder("SELECT user_id AS id, login AS username, first_name AS name, last_name AS surname FROM users WHERE 1=1");

        String updatedSql = buildFilterQuery(sql, username, name, surname);

        return jdbcTemplate.query(updatedSql, new BeanPropertyRowMapper<>(User.class));
    }
}
