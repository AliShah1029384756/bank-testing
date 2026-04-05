package com.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountValidatorTest {

    private AccountValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AccountValidator();
    }

    @Test @Order(1) @Tag("fast")
    void should_ReturnTrue_when_ValidEmailGiven() {
        assertTrue(validator.isValidEmail("shah@gmail.com"));
    }

    @Test @Order(2) @Tag("fast")
    void should_ReturnFalse_when_EmailHasNoAtSign() {
        assertFalse(validator.isValidEmail("shahgmail.com"));
    }

    @Test @Order(3) @Tag("fast")
    void should_ReturnFalse_when_EmailIsNull() {
        assertFalse(validator.isValidEmail(null));
    }

    @Test @Order(4) @Tag("fast")
    void should_ReturnTrue_when_ValidAccountIdGiven() {
        assertTrue(validator.isValidAccountId("ACC1234"));
    }

    @Test @Order(5) @Tag("fast")
    void should_ReturnFalse_when_AccountIdInvalidFormat() {
        assertFalse(validator.isValidAccountId("1234ACC"));
    }

    @Test @Order(6) @Tag("fast")
    void should_ReturnTrue_when_ValidAmountGiven() {
        assertTrue(validator.isValidAmount(500.0));
    }

    @Test @Order(7) @Tag("fast")
    void should_ReturnFalse_when_AmountIsZero() {
        assertFalse(validator.isValidAmount(0));
    }

    @Test @Order(8) @Tag("fast")
    void should_ReturnFalse_when_AmountExceedsLimit() {
        assertFalse(validator.isValidAmount(2000000));
    }

    @Test @Order(9) @Tag("fast")
    void should_ReturnTrue_when_ValidAgeGiven() {
        assertTrue(validator.isValidAge(25));
    }

    @Test @Order(10) @Tag("fast")
    void should_ReturnFalse_when_AgeBelow18() {
        assertFalse(validator.isValidAge(17));
    }
}