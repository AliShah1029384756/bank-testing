package com.bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("ACC1001", "Shah", 1000.0);
    }

    @Test @Order(1) @Tag("fast")
    void should_CreateAccount_when_ValidInputsGiven() {
        assertEquals("ACC1001", account.getAccountId());
        assertEquals(1000.0, account.getBalance());
        assertTrue(account.isActive());
    }

    @Test @Order(2) @Tag("fast")
    void should_IncreaseBalance_when_DepositMade() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
    }

    @Test @Order(3) @Tag("fast")
    void should_DecreaseBalance_when_WithdrawalMade() {
        account.withdraw(200.0);
        assertEquals(800.0, account.getBalance());
    }

    @Test @Order(4) @Tag("fast")
    void should_ThrowException_when_NegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test @Order(5) @Tag("fast")
    void should_ThrowException_when_InsufficientFunds() {
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(9999));
    }

    @Test @Order(6) @Tag("fast")
    void should_ThrowException_when_NegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () ->
                new BankAccount("ACC1002", "Ali", -500));
    }

    @Test @Order(7) @Tag("slow")
    void should_ThrowException_when_DepositOnInactiveAccount() {
        account.deactivate();
        assertThrows(IllegalStateException.class, () -> account.deposit(100));
    }

    @Test @Order(8) @Tag("slow")
    void should_ThrowException_when_WithdrawOnInactiveAccount() {
        account.deactivate();
        assertThrows(IllegalStateException.class, () -> account.withdraw(100));
    }

    @Test @Order(9) @Tag("fast")
    void should_HaveZeroBalance_when_WithdrawExactBalance() {
        account.withdraw(1000.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test @Order(10) @Tag("slow")
    void should_DeactivateAccount_when_DeactivateCalled() {
        account.deactivate();
        assertFalse(account.isActive());
    }
}