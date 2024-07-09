package com.trianee.JPA.embebded;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getId () {
        // สร้าง  ID
        OrderId orderId = new OrderId("123", LocalDateTime.now());
        //  สร้าง Order
        Order order = new Order();
        // set ID ของ order
        order.setId(orderId);
        // check ว่า
        assertEquals(orderId, order.getId());
    }

    @Test
    void getAddress () {
        Order order = new Order();
        Address address = new Address();
        address.setStreetName("Natan");
        address.setHouseNumber("11/2");
        address.setZipCode("1234");
        order.setAddress(address);

        assertEquals(address, order.getAddress());
    }

    @Test
    void getOrderInfo () {
        Order order = new Order();
        order.setOrderInfo("Natan,1234,22");
    }

    @Test
    void getAnotherField () {
        Order order = new Order();
        order.setAnotherField("Sample Data");
    }

    @Test
    void setId () {
        OrderId orderId = new OrderId();
        Address address = new Address("Main Street", "123", "12345");
        String orderInfo = "Test order Info";
        String anotherField = "Another Field";

        Order order = new Order(orderId, address, orderInfo, anotherField);

        OrderId newOrderId = new OrderId();
        order.setId(newOrderId);

        assertEquals(newOrderId, order.getId());
    }

    @Test
    void setAddress () {
        // Arrange
        OrderId orderId = new OrderId();
        Address address = new Address("Main Street", "123", "12345");
        String orderInfo = "Test order Info";
        String anotherField = "Another Field";

        Order order = new Order(orderId, address, orderInfo, anotherField);

        Address newAddress = new Address("Second Street", "456", "54321");

        // Act
        order.setAddress(newAddress);

        // Assert
        assertEquals(newAddress, order.getAddress());
    }

    @Test
    void setOrderInfo () {
        OrderId orderId = new OrderId("user123", LocalDateTime.now());
        Address address = new Address("Main Street", "123", "12345");
        String orderInfo = "Test Order Info";
        String anotherField = "Another Field";

        Order order = new Order(orderId, address, orderInfo, anotherField);

        String newOrderInfo = "Updated Order Info";

        //ACT
        order.setOrderInfo(newOrderInfo);

        //Assert
        assertEquals(newOrderInfo, order.getOrderInfo());
    }

    @Test
    void setAnotherField () {
        //Arrange เตรียมข้อมูล
        OrderId orderId = new OrderId();
        Address address = new Address("Sukumvit Street", "11/6", "112233");
        String orderInfo = "Test Order Info";
        String anotherField = "Another Field";

        Order order = new Order(orderId, address, orderInfo, anotherField);

        String newAnotherField = "Update AnotherField";

        //ACT
        order.setAnotherField(newAnotherField);

        //Assert ตรวจสอบ
        assertEquals(newAnotherField, order.getAnotherField());

    }

    @Test
    void testEquals () {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        OrderId orderId1 = new OrderId("user123", now);
        Address address1 = new Address("Main Street", "123", "12345");
        String orderInfo1 = "Test Order Info";
        String anotherField1 = "Another Field";

        Order order1 = new Order(orderId1, address1, orderInfo1, anotherField1);

        OrderId orderId2 = new OrderId("user123", now);
        Address address2 = new Address("Main Street", "123", "12345");
        String orderInfo2 = "Test Order Info";
        String anotherField2 = "Another Field";

        Order order2 = new Order(orderId2, address2, orderInfo2, anotherField2);

        // Act & Assert
        assertEquals(order1, order2);

        // Testing negative case
        OrderId orderId3 = new OrderId("user456", LocalDateTime.now());
        Address address3 = new Address("Another Street", "456", "67890");
        String orderInfo3 = "Different Order Info";
        String anotherField3 = "Different Field";

        Order order3 = new Order(orderId3, address3, orderInfo3, anotherField3);

        assertNotEquals(order1, order3);
    }

    @Test
    void canEqual () {
        LocalDateTime dateTime = LocalDateTime.now();
        OrderId orderId1 = new OrderId("user123", dateTime);
        Address address1 = new Address("Main Street", "123", "12345");
        String orderInfo1 = "Test Order Info";
        String anotherField1 = "Another Field";

        Order order1 = new Order(orderId1, address1, orderInfo1, anotherField1);

        OrderId orderId2 = new OrderId("user123", dateTime);
        Address address2 = new Address("Main Street", "123", "12345");
        String orderInfo2 = "Test Order Info";
        String anotherField2 = "Another Field";

        Order order2 = new Order(orderId2, address2, orderInfo2, anotherField2);

        // Act & Assert
        assertTrue(order1.canEqual(order2));
        assertEquals(order1, order2);
    }

    @Test
    void testHashCode () {
        // Arrange
        OrderId orderId1 = new OrderId("user123", LocalDateTime.now());
        Address address1 = new Address("Main Street", "123", "12345");
        String orderInfo1 = "Test Order Info";
        String anotherField1 = "Another Field";

        Order order1 = new Order(orderId1, address1, orderInfo1, anotherField1);

        OrderId orderId2 = new OrderId("user123", LocalDateTime.now());
        Address address2 = new Address("Main Street", "123", "12345");
        String orderInfo2 = "Test Order Info";
        String anotherField2 = "Another Field";

        Order order2 = new Order(orderId2, address2, orderInfo2, anotherField2);

        // Act
        int hashCode1 = order1.hashCode();
        int hashCode2 = order2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2, "Hash codes should be equal for equal objects.");

        // Testing consistency
        assertEquals(hashCode1, order1.hashCode(), "Hash code should be consistent.");
    }

    @Test
    void testToString () {
        // Arrange
        OrderId orderId = new OrderId("user123", LocalDateTime.now());
        Address address = new Address("Main Street", "123", "12345");
        String orderInfo = "Test Order Info";
        String anotherField = "Another Field";

        Order order = new Order(orderId, address, orderInfo, anotherField);

        // Act
        String result = order.toString();

        // Assert
        assertNotNull(result, "toString() should not return null.");

        assertTrue(result.contains("OrderId"), "toString() should contain OrderId field.");
        assertTrue(result.contains("Address"), "toString() should contain Address field.");
        assertTrue(result.contains("orderInfo"), "toString() should contain orderInfo field.");
        assertTrue(result.contains("anotherField"), "toString() should contain anotherField field.");

        assertTrue(result.contains(orderId.toString()), "toString() should contain string representation of OrderId.");
        assertTrue(result.contains(address.toString()), "toString() should contain string representation of Address.");
        assertTrue(result.contains(orderInfo), "toString() should contain orderInfo value.");
        assertTrue(result.contains(anotherField), "toString() should contain anotherField value.");
    }
}