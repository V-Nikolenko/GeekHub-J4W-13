package org.geekhub.crypto.history;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository {

    void saveRecord(HistoryRecord record);

    void deleteRecord(int id);

    @NonNull
    Optional<HistoryRecord> getRecord(int id);

    @NonNull
    List<HistoryRecord> getRecords();

    @NonNull
    List<HistoryRecord> getRecords(int userId);

    @NonNull
    List<HistoryRecord> getRecords(@Nullable OffsetDateTime from, @Nullable OffsetDateTime to);

    @NonNull
    List<HistoryRecord> getRecords(int pageNum, int pageSize);

    @NonNull
    List<HistoryRecord> getRecords(int userId, int pageNum, int pageSize);

}
