package com.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InterestCalculatorTest {

    private InterestCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new InterestCalculator();
    }

    @Test @Order(1) @Tag("fast")
    void should_CalculateSimpleInterest_when_ValidInputsGiven() {
        double interest = calc.calculateSimpleInterest(1000, 10, 2);
        assertEquals(200.0, interest, 0.01);
    }

    @Test @Order(2) @Tag("fast")
    void should_ThrowException_when_PrincipalIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateSimpleInterest(0, 10, 2));
    }

    @Test @Order(3) @Tag("fast")
    void should_ThrowException_when_TimeIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateSimpleInterest(1000, 10, 0));
    }

    @Test @Order(4) @Tag("fast")
    void should_ThrowException_when_NegativePrincipal() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateSimpleInterest(-1000, 10, 2));
    }

    @Test @Order(5) @Tag("slow")
    void should_CalculateCompoundInterest_when_ValidInputsGiven() {
        double interest = calc.calculateCompoundInterest(1000, 10, 12, 2);
        assertTrue(interest > 0);
    }

    @Test @Order(6) @Tag("slow")
    void should_ReturnCompoundGreaterThanSimple_when_SameInputs() {
        double simple = calc.calculateSimpleInterest(1000, 10, 2);
        double compound = calc.calculateCompoundInterest(1000, 10, 12, 2);
        assertTrue(compound > simple);
    }

    @Test @Order(7) @Tag("fast")
    void should_CalculateFinalAmount_when_ValidInputsGiven() {
        double amount = calc.calculateFinalAmount(1000, 10, 2);
        assertEquals(1200.0, amount, 0.01);
    }

    @Test @Order(8) @Tag("slow")
    void should_ThrowException_when_NegativeRate() {
        assertThrows(IllegalArgumentException.class, () ->
                calc.calculateSimpleInterest(1000, -5, 2));
    }
}