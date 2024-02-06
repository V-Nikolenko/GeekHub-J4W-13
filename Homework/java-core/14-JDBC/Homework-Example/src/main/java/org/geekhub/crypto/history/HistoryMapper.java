package org.geekhub.crypto.history;

import org.geekhub.crypto.encoding.EncodingAlgorithm;
import org.geekhub.crypto.encoding.EncodingOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

class HistoryMapper {

    private HistoryMapper() {
    }

    static HistoryRecord mapToPojo(ResultSet rs, int rowNum) throws SQLException {
        return new HistoryRecord(
            rs.getInt("record_id"),
            rs.getInt("user_id"),
            EncodingOperation.valueOf(rs.getString("operation")),
            EncodingAlgorithm.valueOf(rs.getString("algorithm")),
            rs.getString("original_text"),
            rs.getString("encoded_text"),
            rs.getTimestamp("date").toInstant().atOffset(ZoneOffset.UTC)
        );
    }
}
