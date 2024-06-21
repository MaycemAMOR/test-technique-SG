package com.sg.ceci;

import com.sg.ceci.model.Employees;
import com.sg.ceci.model.Revenue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sg.ceci.model.Type.*;
import static com.sg.ceci.service.CalculateTotalRevenue.calculateCeciTax;
import static com.sg.ceci.service.CalculateTotalRevenue.calculateTotalRevenue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SgApplicationTests {
    @Test
    public void testCalculateCeciTax() {
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
                                .value(BigDecimal.valueOf(20000.36)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(15000.00)).build(),
                        Revenue.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false),

                new Employees(Arrays.asList(
                        Revenue.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(2000.36)).build(),
                        Revenue.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(1500.00)).build(),
                        Revenue.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(5000.69)).build()
                ), true)
        );

        double expectedTax = (10000.00 + 7000.99 + 20000.36 + 15000.00) * 0.6;
        double actualTotalRevenue = calculateTotalRevenue(employeesWithThreshold);
        double actualTax = calculateCeciTax(actualTotalRevenue);
        assertEquals(expectedTax, actualTax);
    }

}
