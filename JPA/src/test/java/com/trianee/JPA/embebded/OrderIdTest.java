package com.trianee.JPA.embebded;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderIdTest {

    @Test
    void getUsername () {
        // Arrange
        String expectedUsername = "user123";
        LocalDateTime orderDate = LocalDateTime.now();
        OrderId orderId = new OrderId(expectedUsername, orderDate);

        // Act
        String actualUsername = orderId.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername, "Username should match");
    }

    @Test
    void getOrderDate () {
        // Arrange
        String username = "user123";
        LocalDateTime expectedOrderDate = LocalDateTime.now();
        OrderId orderId = new OrderId(username, expectedOrderDate);

        // Act
        LocalDateTime actualOrderDate = orderId.getOrderDate();

        // Assert
        assertEquals(expectedOrderDate, actualOrderDate, "Order date should match");
    }

    @Test
    void setUsername () {
        // Arrange
        OrderId orderId = new OrderId("user123", LocalDateTime.now());
        String newUsername = "newUser";

        // Act
        orderId.setUsername(newUsername);

        // Assert
        assertEquals(newUsername, orderId.getUsername(), "Username should be updated");
    }

    @Test
    void setOrderDate () {
        // Arrange
        OrderId orderId = new OrderId("user123", LocalDateTime.now());
        LocalDateTime newOrderDate = LocalDateTime.of(2023, 1, 1, 12, 0);

        // Act
        orderId.setOrderDate(newOrderDate);

        // Assert
        assertEquals(newOrderDate, orderId.getOrderDate(), "Order date should be updated");
    }

    @Test
    void testEquals () {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        OrderId orderId1 = new OrderId("user123", now);
        OrderId orderId2 = new OrderId("user123", now);

        // Act & Assert
        assertEquals(orderId1, orderId2, "OrderIds should be equal");
    }

    @Test
    void canEqual () {
        // Arrange
        OrderId orderId1 = new OrderId("user123", LocalDateTime.now());

        // Act & Assert
        assertTrue(orderId1.canEqual(new OrderId("user123", LocalDateTime.now())));
    }

    @Test
    void testHashCode () {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        OrderId orderId1 = new OrderId("user123", now);
        OrderId orderId2 = new OrderId("user123", now);

        // Act & Assert
        assertEquals(orderId1.hashCode(), orderId2.hashCode(), "Hash codes should be equal");
    }

    @Test
    void testToString () {
        // Arrange
        OrderId orderId = new OrderId("user123", LocalDateTime.now());

        // Act
        String toStringResult = orderId.toString();

        // Assert
        assertTrue(toStringResult.contains("username=user123") && toStringResult.contains("orderDate="));
    }
}