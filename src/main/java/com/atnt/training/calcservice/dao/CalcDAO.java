package com.atnt.training.calcservice.dao;

import com.atnt.training.calcservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
// Data Access Object
public class CalcDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    final String INSERT_USER = "INSERT INTO USERS (name, lastName) values (:name, :lastName)";
    final String INSERT_USER_ADDRESS = "insert into address () values ()";
    final String GET_USER = "select id, name, lastName from users where id = :id";

    public void saveUser(final String name, final String lastName) {

        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("lastName", lastName);
        int retVal = namedParameterJdbcTemplate.update(INSERT_USER, params);
        if (retVal > 0) {

        }
    }

    public User getUser(final int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return namedParameterJdbcTemplate.queryForObject(GET_USER, params,
                BeanPropertyRowMapper.newInstance(User.class));
    }
}
