package org.geekhub.crypto.users;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean userExists(int userId) {
        String query = "SELECT EXISTS (SELECT 1 FROM users WHERE user_id = :userId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId);

        Boolean result = jdbcTemplate.queryForObject(query, params, Boolean.class);
        return Boolean.TRUE.equals(result);
    }
}
