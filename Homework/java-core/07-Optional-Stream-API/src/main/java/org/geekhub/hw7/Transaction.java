package org.geekhub.hw7;

import javax.annotation.Nonnull;
import java.time.LocalDate;

public record Transaction(double amount, @Nonnull String category, @Nonnull LocalDate date) {
}
