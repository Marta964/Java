package com.example.convert.serviceTest;

import com.example.convert.service.CounterService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterServiceTest {
    @Test
    public void testIncrementRequestCount() {
        // Arrange
        int initialCount = CounterService.getRequestCount();

        // Act
        CounterService.incrementRequestCount();

        // Assert
        assertEquals(initialCount + 1, CounterService.getRequestCount());
    }

    @Test
    public void testGetRequestCount() {
        // Arrange
        int expectedCount = CounterService.getRequestCount();

        // Act
        int actualCount = CounterService.getRequestCount();

        // Assert
        assertEquals(expectedCount, actualCount);
    }
}
