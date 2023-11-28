package org.geekhub.hw7;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TransactionAnalyzer {

    /**
     * Finds the biggest transaction in a specified category.
     *
     * @param category The category to search in.
     * @return An Optional containing the biggest transaction in the given category, or an empty Optional if no transactions are found.
     */
    Optional<Transaction> getBiggestTransactionInCategory(String category);

    /**
     * Calculates the total amount spent on a specific date.
     *
     * @param date The date for which the total spending is to be calculated.
     * @return The total amount spent on the given date.
     */
    double getTotalSpentForDate(LocalDate date);

    /**
     * Retrieves all transactions for a given category on a specific date.
     *
     * @param category The category of the transactions.
     * @param date     The date of the transactions.
     * @return A list of transactions that match the given category and date.
     */
    List<Transaction> getTransactionsByCategoryAndDate(String category, LocalDate date);

    /**
     * Calculates the total amount spent in each category.
     * The results are sorted by amount in descending order.
     *
     * @return A map with category names as keys and total spent amounts as values, sorted by the spent amount.
     */
    Map<String, Double> getSpentAmountByCategory();

    /**
     * Identifies the date with the highest total expenses.
     *
     * @return An Optional containing the date with the most expenses, or an empty Optional if no transactions are present.
     */
    Optional<LocalDate> getDateWithMostExpenses();

    /**
     * Calculates the average transaction amount in each category across all transactions.
     *
     * @return A map with category names as keys and the average transaction amounts as values.
     */
    Map<String, Double> getAverageSpendingPerCategory();

    /**
     * Determines the most popular category based on the number of transactions.
     * The most popular category is the one with the highest number of transactions.
     *
     * @return An Optional containing the name of the most popular category, or an empty Optional if there are no transactions.
     */
    Optional<String> getMostPopularCategory();

    /**
     * Calculates the distribution of spending across categories as a percentage of the total spending.
     *
     * @return A map with category names as keys and their corresponding percentages of the total spending as values.
     */
    Map<String, Double> getCategoryWiseDistribution();
}
