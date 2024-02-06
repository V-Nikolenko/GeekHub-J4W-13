package org.geekhub.crypto.history;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class HistoryService{

    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public void saveRecord(@NonNull HistoryRecord record) {
        historyRepository.saveRecord(record);
    }

    public void deleteRecord(int id) {
        historyRepository.deleteRecord(id);
    }

    @NonNull
    public HistoryRecord getRecord(int id) {
        return historyRepository.getRecord(id)
            .orElseThrow(() -> new IllegalArgumentException("Record with id " + id + " not found"));
    }

    @NonNull
    public List<HistoryRecord> getRecords() {
        return historyRepository.getRecords();
    }

    @NonNull
    public List<HistoryRecord> getRecords(int userId) {
        return historyRepository.getRecords(userId);
    }

    @NonNull
    public List<HistoryRecord> getRecords(@Nullable OffsetDateTime from, @Nullable OffsetDateTime to) {
        if (Objects.nonNull(from) && Objects.nonNull(to) && from.isAfter(to)) {
            throw new IllegalArgumentException("From date must be before to date");
        }

        return historyRepository.getRecords(from, to);
    }

    @NonNull
    public List<HistoryRecord> getRecords(int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and page size must be greater than 0");
        }

        return historyRepository.getRecords(pageNum, pageSize);
    }

    @NonNull
    public List<HistoryRecord> getRecords(int userId, int pageNum, int pageSize) {
        if (pageNum < 1 || pageSize < 1) {
            throw new IllegalArgumentException("Page number and page size must be greater than 0");
        }

        return historyRepository.getRecords(userId, pageNum, pageSize);
    }
}
