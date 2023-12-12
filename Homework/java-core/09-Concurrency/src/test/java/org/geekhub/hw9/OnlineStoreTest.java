package org.geekhub.hw9;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OnlineStoreTest {

    private OnlineStore onlineStore;

    @BeforeEach
    void setUp() {
        onlineStore = new OnlineStore();
    }

    @Test
    void testPurchase() throws ExecutionException, InterruptedException {
        onlineStore.addProduct("EcoFlow", 10);
        onlineStore.addProduct("Snickers", 20);

        assertTrue(onlineStore.purchase("EcoFlow", 5).get());
        assertTrue(onlineStore.purchase("EcoFlow", 5).get());
        assertEquals(0, onlineStore.getProductQuantity("EcoFlow"));

        assertTrue(onlineStore.purchase("Snickers", 5).get());
        assertFalse(onlineStore.purchase("Snickers", 25).get());
        assertEquals(15, onlineStore.getProductQuantity("Snickers"));
    }

    @Test
    void testConcurrentPurchases() throws InterruptedException {
        onlineStore.addProduct("EcoFlow", 1000);
        onlineStore.addProduct("Snickers", 990);

        List<Future<Boolean>> results = simulateBlackFridayPurchase(
            onlineStore, 2, 5, 99
        );

        assertAllPurchasesAreSuccessful(results);
        assertEquals(1980, onlineStore.getTotalSales());
        assertEquals(10, onlineStore.getProductQuantity("EcoFlow"));
        assertEquals(0, onlineStore.getProductQuantity("Snickers"));
    }

    @SuppressWarnings({"ResultOfMethodCallIgnored", "SameParameterValue"})
    private static List<Future<Boolean>> simulateBlackFridayPurchase(
        OnlineStore onlineStore,
        int numPurchasesPerClient,
        int purchaseQuantityPerClient,
        int clientCount
    ) throws InterruptedException {
        List<Future<Boolean>> results = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Callable<Boolean> purchaseTask = () -> {
            for (int i = 0; i < numPurchasesPerClient; i++) {
                results.add(onlineStore.purchase("EcoFlow", purchaseQuantityPerClient));
                results.add(onlineStore.purchase("Snickers", purchaseQuantityPerClient));
            }
            return true;
        };

        for (int i = 0; i < clientCount; i++) {
            executorService.submit(purchaseTask);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        return results;
    }

    private static void assertAllPurchasesAreSuccessful(List<Future<Boolean>> results) {
        results.forEach(result -> {
            try {
                assertTrue(result.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
