package com.bank;

import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("Complete Bank Testing Suite")
@SelectClasses({
        BankAccountTest.class,
        CustomerTest.class,
        TransactionTest.class,
        LoanCalculatorTest.class,
        AccountValidatorTest.class,
        InterestCalculatorTest.class,
        DataDrivenTest.class
})
public class BankTestSuite {
}