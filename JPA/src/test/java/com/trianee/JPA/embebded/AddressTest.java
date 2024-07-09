package com.trianee.JPA.embebded;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    @Transactional
    void getStreetName () {
        // Arrange
        String expectedStreetName = "Main Street";
        Address address = new Address(expectedStreetName, "123", "12345");
        OrderId orderId = new OrderId("user1", LocalDateTime.now());
        Order order = new Order(orderId, address, "Order Info", "Another Field");

        // Act
        String actualStreetName = order.getAddress().getStreetName();

        // Assert
        assertEquals(expectedStreetName, actualStreetName, "Street name should match");
    }

    @Test
    void getHouseNumber () {
        // Arrange
        String expectedHouseNumber = "123";
        Address address = new Address("Main Street", expectedHouseNumber, "12345");
        OrderId orderId = new OrderId("user1", LocalDateTime.now());
        Order order = new Order(orderId, address, "Order Info", "Another Field");

        //Act
        String actualHouseNumber = order.getAddress().getHouseNumber();

        //Assert
        assertEquals(expectedHouseNumber, actualHouseNumber, "HouseNumber should match");
    }

    @Test
    void getZipCode () {
        //Arrange

        String expectedZipcode = "12345";
        Address address = new Address("Main Street", "123", expectedZipcode);
        OrderId orderId = new OrderId("user1", LocalDateTime.now());
        Order order = new Order(orderId, address, "Order Info", "Another Field");

        //Act
        String actualZipcode = order.getAddress().getZipCode();

        //Assert
        assertEquals(expectedZipcode, actualZipcode, "Zip Code should match");

    }

    @Test
    void setStreetName () {
        //Arrange
        Address address = new Address("Main Street", "123", "12345");
        String newStreet = "New Street";
        OrderId orderId = new OrderId("user3", LocalDateTime.now());

        Order order = new Order(orderId, address, "Order Info", "Another Field");

        //Act
        address.setStreetName(newStreet);
        String actualStreetName = order.getAddress().getStreetName();

        //Assert
        assertEquals(newStreet, actualStreetName, "Street Name should be updated");
    }

    @Test
    void setHouseNumber () {
        //Arrange
        Address address = new Address("Main Street", "123", "12345");
        String newHouseNumber = "New HouseNumber";
        OrderId orderId = new OrderId("user4", LocalDateTime.now());
        Order order = new Order(orderId, address, "Order Info", "Another Field");

        address.setHouseNumber(newHouseNumber);
        String actualHouseNumber = order.getAddress().getHouseNumber();

        assertEquals(newHouseNumber, actualHouseNumber, "House Name should be updated");
    }

    @Test
    void setZipCode () {
        //Arrange
        Address address = new Address("Main Street", "123", "12345");
        String newZipCode = "67890";
        OrderId orderId = new OrderId("user5", LocalDateTime.now());
        Order order = new Order(orderId, address, "Order Info", "Another Filed");

        //Act

        address.setZipCode(newZipCode);
        String actualZipcode = order.getAddress().getZipCode();

        //Assert
        assertEquals(newZipCode, actualZipcode, "Zip Code Should be update");
    }

    @Test
    void testEquals () {

        //Arrange
        Address address1 = new Address("Main Street", "123", "12345");
        Address address2 = new Address("Main Street", "123", "12345");

        //Act
        boolean result = address1.equals(address2);

        //Assert
        assertTrue(result, "Addresses should be equal");
    }

    @Test
    void canEqual () {
        //Arrange

        Address address1 = new Address("Main Street", "123", "12345");
        Address address2 = new Address("New Street", "999", "1111");

        //Act
        boolean result = address1.canEqual(address2);

        //Assert
        assertTrue(result, "Addresses should be can equal");
    }

    @Test
    void testHashCode () {
        // Arrange
        Address address1 = new Address("Main Street", "123", "12345");
        Address address2 = new Address("Main Street", "123", "12345");

        // Act
        int hashCode1 = address1.hashCode();
        int hashCode2 = address2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2, "Hash codes should be equal for equal objects");
    }

    @Test
    void testToString () {
        // Arrange
        Address address = new Address("Main Street", "123", "12345");

        // Act
        String str = address.toString();

        // Assert
        assertTrue(str.contains("Address"), "String representation should contain class name");
        assertTrue(str.contains("streetName=" + address.getStreetName()), "String representation should contain street name");
        assertTrue(str.contains("houseNumber=" + address.getHouseNumber()), "String representation should contain house number");
        assertTrue(str.contains("zipCode=" + address.getZipCode()), "String representation should contain zip code");
    }
}