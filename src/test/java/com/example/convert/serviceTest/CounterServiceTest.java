package com.example.convert.serviceTest;

import com.example.convert.service.CounterService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CounterServiceTest {
    @Test
    void testIncrementRequestCount() {
        // Arrange
        int initialCount = CounterService.getRequestCount();

        // Act
        CounterService.incrementRequestCount();

        // Assert
        assertEquals(initialCount + 1, CounterService.getRequestCount());
    }

    @Test
    void testGetRequestCount() {
        // Arrange
        int expectedCount = CounterService.getRequestCount();

        // Act
        int actualCount = CounterService.getRequestCount();

        // Assert
        assertEquals(expectedCount, actualCount);
    }
}
