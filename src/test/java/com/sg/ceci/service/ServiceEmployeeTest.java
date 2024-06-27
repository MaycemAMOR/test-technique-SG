package com.sg.ceci.service;

import com.sg.ceci.model.Earning;
import com.sg.ceci.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sg.ceci.model.Type.*;
import static com.sg.ceci.service.ServiceEmployee.computeTotalIncomeDouble;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceEmployeeTest {

    private List<Employee> employeesList;

    @BeforeEach
    public void setUp() {
        employeesList = Arrays.asList(
                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(10000.00)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(70000.99)).build(),
                        Earning.builder()
                                .type(RTT)
                                .value(BigDecimal.valueOf(20000.36)).build()
                ), false),
                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(15000.00)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(79000.99)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20000.36)).build()
                ), false),
                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(1000.00)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(700.99)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), true) // Intern
        );
    }

    @Test
    public void testComputeTotalIncome() {
        double expectedTotalIncome = 10000.00 + 70000.99 + 20000.36 + 15000.00 + 79000.99;
        double actualTotalIncome = computeTotalIncomeDouble(employeesList);
        assertEquals(expectedTotalIncome, actualTotalIncome);
    }

    @Test
    public void testComputeTotalIncomeWithThreshold() {
        List<Employee> employeesWithThreshold = Arrays.asList(
                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(10000.00)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(7000.99)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false),
                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(10000.00)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(7000.99)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false)
        );

        double expectedTotalIncome = 10000.00 + 7000.99 + 10000.00 + 7000.99;
        double actualTotalIncome = computeTotalIncomeDouble(employeesWithThreshold);
        assertEquals(expectedTotalIncome, actualTotalIncome);
    }
}
