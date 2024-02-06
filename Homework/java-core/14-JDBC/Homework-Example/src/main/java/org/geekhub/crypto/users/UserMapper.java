package org.geekhub.crypto.users;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserMapper {

    private UserMapper() {
    }

    static User mapToPojo(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getInt("user_id"),
            rs.getString("username")
        );
    }
}
