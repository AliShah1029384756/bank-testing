package com.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "Shah", "shah@gmail.com", 22);
    }

    @Test @Order(1) @Tag("fast")
    void should_CreateCustomer_when_ValidInputsGiven() {
        assertEquals("C001", customer.getCustomerId());
        assertEquals("Shah", customer.getName());
        assertEquals(22, customer.getAge());
    }

    @Test @Order(2) @Tag("fast")
    void should_ThrowException_when_AgeBelow18() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("C002", "Ali", "ali@gmail.com", 16));
    }

    @Test @Order(3) @Tag("fast")
    void should_ThrowException_when_NameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("C003", "", "test@gmail.com", 25));
    }

    @Test @Order(4) @Tag("fast")
    void should_UpdateEmail_when_SetEmailCalled() {
        customer.setEmail("new@gmail.com");
        assertEquals("new@gmail.com", customer.getEmail());
    }

    @Test @Order(5) @Tag("fast")
    void should_CreateCustomer_when_AgeIsExactly18() {
        Customer c = new Customer("C004", "Young", "young@gmail.com", 18);
        assertEquals(18, c.getAge());
    }

    @Test @Order(6) @Tag("slow")
    void should_ThrowException_when_NameIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("C005", null, "test@gmail.com", 25));
    }

    @Test @Order(7) @Tag("fast")
    void should_ReturnCorrectEmail_when_EmailSet() {
        assertEquals("shah@gmail.com", customer.getEmail());
    }

    @Test @Order(8) @Tag("slow")
    void should_ThrowException_when_AgeIs17Boundary() {
        assertThrows(IllegalArgumentException.class, () ->
                new Customer("C006", "Test", "test@gmail.com", 17));
    }
}