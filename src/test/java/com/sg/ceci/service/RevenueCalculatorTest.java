package com.sg.ceci.service;

import com.sg.ceci.model.Employees;
import com.sg.ceci.model.Revenue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sg.ceci.model.Type.*;
import static com.sg.ceci.service.CalculateTotalRevenue.calculateTotalRevenue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RevenueCalculatorTest {

    private List<Employees> employeesList;

    @BeforeEach
    public void setUp() {
        employeesList = Arrays.asList(
                new Employees(Arrays.asList(
                        Revenue.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(10000.00)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(70000.99)).build(),
                        Revenue.builder()
                                .type(RTT)
                                .value(BigDecimal.valueOf(20000.36)).build()
                ), false),
                new Employees(Arrays.asList(
                        Revenue.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(15000.00)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(79000.99)).build(),
                        Revenue.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20000.36)).build()
                ), false),
                new Employees(Arrays.asList(
                        Revenue.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(1000.00)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(700.99)).build(),
                        Revenue.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), true) // Intern
        );
    }

    @Test
    public void testCalculateTotalRevenue() {
        double expectedTotalRevenue = 10000.00 + 70000.99 + 20000.36 + 15000.00 + 79000.99;
        double actualTotalRevenue = calculateTotalRevenue(employeesList);
        assertEquals(expectedTotalRevenue, actualTotalRevenue);
    }

    @Test
    public void testCalculateTotalRevenueWithThreshold() {
        List<Employees> employeesWithThreshold = Arrays.asList(
                new Employees(Arrays.asList(
                        Revenue.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(10000.00)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(7000.99)).build(),
                        Revenue.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false),
                new Employees(Arrays.asList(
                        Revenue.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(10000.00)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(7000.99)).build(),
                        Revenue.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false)
        );

        double expectedTotalRevenue = 10000.00 + 7000.99 + 10000.00 + 7000.99;
        double actualTotalRevenue = calculateTotalRevenue(employeesWithThreshold);
        assertEquals(expectedTotalRevenue, actualTotalRevenue);
    }
}
