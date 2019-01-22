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
public class UserDao implements DaoInterface<User> {
    /**
     * I'm autowiring jdbc template using the properties i have configured in application.properties
     * spring automatically detects and create jdbc template object using the configuration
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * @return list of User names
     */
   /* public List<String> getAll() {
        List<String> listOfUserNames = new ArrayList<>();
        listOfUserNames.addAll(jdbcTemplate.queryForList("select concat(Name,' ',Surname) from users;", String.class));
        return listOfUserNames;
    }*/
    @Override
    public void create(User user) {
        jdbcTemplate.update("INSERT INTO `dailyplanner`.`users` (`Name`,`Surname`,`Date_of_birth`,`Nickname`,`Password`,`Email`)VALUES(?,?,?,?,?,?);",
                user.getName(), user.getSurname(), user.getDateOfBirth(), user.getNickname(), user.getPassword(), user.getEmail());
        //todo add hash of password
        //todo add check of adding when create findBy and return boolean
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("INSERT INTO `dailyplanner`.`users` (`idUser`,`Name`,`Surname`,`Date_of_birth`,`Nickname`,`Password`,`Email`)VALUES(?,?,?,?,?,?,?);",
                user.getId(), user.getName(), user.getSurname(), user.getDateOfBirth(), user.getNickname(), user.getPassword(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM `dailyplanner`.`users` where idUser=?", id);
    }
    @Override
    public List<User> getAll() {
        List<User> listOfUsers;
        listOfUsers = jdbcTemplate.query("select * from users;", new UserRowMapper());
        return listOfUsers;
    }
    @Override
    public User getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM `dailyplanner`.`users` WHERE idUser=?", new UserRowMapper(), id);

    }

    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM `dailyplanner`.`users` WHERE Email=?", new UserRowMapper(), email);
    }
    public User findByNickname(String nickname) {
        return jdbcTemplate.queryForObject("SELECT * FROM `dailyplanner`.`users` WHERE Nickname=?", new UserRowMapper(), nickname);
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
                rs.getTimestamp("Date_of_birth").toLocalDateTime().toLocalDate(),
                rs.getString("Nickname"),
                rs.getString("Password"),
                rs.getString("Email"));
        return user;
    }
}
