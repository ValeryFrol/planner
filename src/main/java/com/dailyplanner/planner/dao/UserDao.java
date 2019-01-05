package com.dailyplanner.planner.dao;

import com.dailyplanner.planner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    /**
     * I'm autowiring jdbc template using the properties i have configured in application.properties
     * spring automatically detects and create jdbc template object using the configuration
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @return list of User names
     */
    public List<String> getAllUserNames() {
        List<String> listOfUserNames = new ArrayList<>();
        listOfUserNames.addAll(jdbcTemplate.queryForList("select concat(Name,' ',Surname) from users;", String.class));
        return listOfUserNames;
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO `dailyplanner`.`users` (`Name`,`Surname`,`Date_of_birth`,`Nickname`,`Password`,`Email`)VALUES(?,?,?,?,?,?);",
                user.getName(), user.getSurname(), user.getDateOfBirth(), user.getNickname(), user.getPassword(), user.getEmail());
        //todo add hash of password
        //todo add check of adding when create findBy and return boolean
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        listOfUsers = jdbcTemplate.query("select * from users;", new UserRowMapper());
        return listOfUsers;
    }
    public void changeUser(User user){
        jdbcTemplate.update("INSERT INTO `dailyplanner`.`users` (`idUser`,`Name`,`Surname`,`Date_of_birth`,`Nickname`,`Password`,`Email`)VALUES(?,?,?,?,?,?,?);",
                user.getId(),user.getName(), user.getSurname(), user.getDateOfBirth(), user.getNickname(), user.getPassword(), user.getEmail());

    }
}

/**
 * mapping rows in DB tables to objects
 */
class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getInt("idUser"),
                rs.getString("Name"),
                rs.getString("Surname"),
                rs.getDate("Date_of_birth"),
                rs.getString("Nickname"),
                rs.getString("Password"),
                rs.getString("Email"));
        return user;
    }
}
