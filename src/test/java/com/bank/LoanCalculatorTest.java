package com.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanCalculatorTest {

    private LoanCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new LoanCalculator();
    }

    @Test @Order(1) @Tag("fast")
    void should_CalculateMonthlyPayment_when_ValidInputsGiven() {
        double payment = calc.calculateMonthlyPayment(100000, 12, 12);
        assertTrue(payment > 0);
    }

    @Test @Order(2) @Tag("fast")
    void should_ThrowException_when_PrincipalIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateMonthlyPayment(0, 12, 12));
    }

    @Test @Order(3) @Tag("fast")
    void should_ThrowException_when_MonthsIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateMonthlyPayment(10000, 12, 0));
    }

    @Test @Order(4) @Tag("fast")
    void should_ThrowException_when_NegativeRate() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateMonthlyPayment(10000, -1, 12));
    }

    @Test @Order(5) @Tag("slow")
    void should_CalculateCorrectly_when_RateIsZero() {
        double payment = calc.calculateMonthlyPayment(12000, 0, 12);
        assertEquals(1000.0, payment, 0.01);
    }

    @Test @Order(6) @Tag("slow")
    void should_ReturnTotalGreaterThanPrincipal_when_RateIsPositive() {
        double total = calc.calculateTotalPayment(100000, 12, 12);
        assertTrue(total > 100000);
    }

    @Test @Order(7) @Tag("slow")
    void should_ReturnPositiveInterest_when_ValidInputs() {
        double interest = calc.calculateInterest(100000, 12, 12);
        assertTrue(interest > 0);
    }

    @Test @Order(8) @Tag("fast")
    void should_ThrowException_when_NegativePrincipal() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateMonthlyPayment(-1000, 12, 12));
    }
}