package com.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionTest {

    @Test @Order(1) @Tag("fast")
    void should_CreateTransaction_when_ValidDepositGiven() {
        Transaction t = new Transaction("T001", "DEPOSIT", 500.0);
        assertEquals("DEPOSIT", t.getType());
        assertEquals(500.0, t.getAmount());
    }

    @Test @Order(2) @Tag("fast")
    void should_CreateTransaction_when_ValidWithdrawalGiven() {
        Transaction t = new Transaction("T002", "WITHDRAWAL", 200.0);
        assertEquals("WITHDRAWAL", t.getType());
    }

    @Test @Order(3) @Tag("fast")
    void should_CreateTransaction_when_ValidTransferGiven() {
        Transaction t = new Transaction("T003", "TRANSFER", 300.0);
        assertEquals("TRANSFER", t.getType());
    }

    @Test @Order(4) @Tag("fast")
    void should_ThrowException_when_AmountIsZero() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("T004", "DEPOSIT", 0));
    }

    @Test @Order(5) @Tag("fast")
    void should_ThrowException_when_AmountIsNegative() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("T005", "DEPOSIT", -100));
    }

    @Test @Order(6) @Tag("fast")
    void should_ThrowException_when_InvalidType() {
        assertThrows(IllegalArgumentException.class, () ->
                new Transaction("T006", "INVALID", 100));
    }

    @Test @Order(7) @Tag("slow")
    void should_HaveTimestamp_when_TransactionCreated() {
        Transaction t = new Transaction("T007", "DEPOSIT", 100);
        assertNotNull(t.getTimestamp());
    }

    @Test @Order(8) @Tag("fast")
    void should_ReturnCorrectId_when_TransactionCreated() {
        Transaction t = new Transaction("T008", "DEPOSIT", 100);
        assertEquals("T008", t.getTransactionId());
    }
}