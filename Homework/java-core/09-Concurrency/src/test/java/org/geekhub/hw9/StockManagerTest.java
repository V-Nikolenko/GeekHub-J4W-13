package org.geekhub.hw9;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockManagerTest {

    private OnlineStore onlineStore;
    private StockManager stockManager;

    @BeforeEach
    void setUp() {
        onlineStore = new OnlineStore();
        stockManager = new StockManager(onlineStore, 1, 1);
    }

    @Test // risky test that may fail, but we assume that the OS scheduled thread execution as expected
    @SuppressWarnings("java:S2925") // suppressed because we want to test the homework code
    void testRestocking() throws InterruptedException {
        onlineStore.addProduct("EcoFlow", 10);
        onlineStore.addProduct("Snickers", 5);

        Thread.sleep(2100);

        assertEquals(12, onlineStore.getProductQuantity("EcoFlow"));
        assertEquals(7, onlineStore.getProductQuantity("Snickers"));
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        stockManager.shutdown();
    }
}
