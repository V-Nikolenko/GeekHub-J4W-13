package org.geekhub.crypto.history;

import org.geekhub.crypto.encoding.EncodingAlgorithm;
import org.geekhub.crypto.encoding.EncodingOperation;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.OffsetDateTime;

public record HistoryRecord(
    @Nullable Integer recordId,
    int userId,
    @NonNull EncodingOperation operation,
    @NonNull EncodingAlgorithm algorithm,
    @NonNull String originalText,
    @NonNull String encodedText,
    @NonNull OffsetDateTime date
) {

    public HistoryRecord(int userId,
                         @NonNull EncodingOperation operation,
                         @NonNull EncodingAlgorithm algorithm,
                         @NonNull String originalText,
                         @NonNull String encodedText,
                         @NonNull OffsetDateTime date) {
        this(null, userId, operation, algorithm, originalText, encodedText, date);
    }

    public static HistoryRecordBuilder builder() {
        return new HistoryRecordBuilder();
    }

    public static final class HistoryRecordBuilder {
        private int userId;
        private EncodingOperation operation;
        private EncodingAlgorithm algorithm;
        private String originalText;
        private String encodedText;
        private OffsetDateTime date;

        private HistoryRecordBuilder() {
        }

        public HistoryRecordBuilder withUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public HistoryRecordBuilder withOperation(EncodingOperation operation) {
            this.operation = operation;
            return this;
        }

        public HistoryRecordBuilder withAlgorithm(EncodingAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public HistoryRecordBuilder withOriginalText(String originalText) {
            this.originalText = originalText;
            return this;
        }

        public HistoryRecordBuilder withEncodedText(String encodedText) {
            this.encodedText = encodedText;
            return this;
        }

        public HistoryRecordBuilder withDate(OffsetDateTime date) {
            this.date = date;
            return this;
        }

        public HistoryRecord build() {
            return new HistoryRecord(userId, operation, algorithm, originalText, encodedText, date);
        }
    }
}
