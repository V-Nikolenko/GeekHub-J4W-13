package org.geekhub.crypto;

import org.geekhub.crypto.encoding.EncodingAlgorithm;
import org.geekhub.crypto.encoding.EncodingOperation;
import org.geekhub.crypto.encoding.EncodingService;
import org.geekhub.crypto.history.HistoryRecord;
import org.geekhub.crypto.history.HistoryService;
import org.geekhub.crypto.users.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class UserInteractionService {

    private final EncodingAlgorithm encodingAlgorithm;
    private final EncodingOperation encodingOperation;
    private final String inputMessage;
    private final int activeUserId;

    private final EncodingService encodingService;
    private final HistoryService historyService;
    private final UserService userService;

    public UserInteractionService(@Value("${active.user.id}") int activeUserId,
                                  @Value("${active.user.inputMessage}") String inputMessage,
                                  @Value("${active.codec.algorithm}") String encodingAlgorithm,
                                  @Value("${active.codec.operation}") String encodingOperation,
                                  EncodingService encodingService,
                                  HistoryService historyService,
                                  UserService userService) {
        this.encodingAlgorithm = EncodingAlgorithm.valueOf(encodingAlgorithm);
        this.encodingOperation = EncodingOperation.valueOf(encodingOperation);
        this.inputMessage = inputMessage;
        this.activeUserId = activeUserId;
        this.encodingService = encodingService;
        this.historyService = historyService;
        this.userService = userService;
    }

    public void interactWithApplication() {
        if (!userService.isUserExist(activeUserId)) {
            System.err.println("User with id " + activeUserId + " not exists");
            return;
        }

        String encodedMessage = encodingService.encode(encodingAlgorithm, encodingOperation, inputMessage);

        HistoryRecord historyRecord = HistoryRecord.builder()
            .withUserId(activeUserId)
            .withAlgorithm(encodingAlgorithm)
            .withOperation(encodingOperation)
            .withOriginalText(inputMessage)
            .withEncodedText(encodedMessage)
            .withDate(OffsetDateTime.now())
            .build();

        historyService.saveRecord(historyRecord);
        System.out.println("Original message:" + inputMessage + "\nEncoded message: " + encodedMessage);

//        All history for specific date range
        OffsetDateTime from = OffsetDateTime.now().minusMonths(1);
        OffsetDateTime to = OffsetDateTime.now();
        List<HistoryRecord> historyDateRange = historyService.getRecords(from, to);
        System.out.println("History for the last month: " + historyDateRange);


//        History of a user paginated
        List<HistoryRecord> activeUserHistory = historyService.getRecords(activeUserId, 1, 2);
        System.out.println(activeUserHistory);
    }
}
