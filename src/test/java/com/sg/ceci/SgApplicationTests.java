package com.sg.ceci;

import com.sg.ceci.model.Earning;
import com.sg.ceci.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sg.ceci.model.Type.*;
import static com.sg.ceci.service.ServiceEmployee.computeCeciTax;
import static com.sg.ceci.service.ServiceEmployee.computeTotalIncome;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SgApplicationTests {

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
                                .value(BigDecimal.valueOf(7000.99)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false),
                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(20000.36)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(15000.00)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(20.36)).build()
                ), false),

                new Employee(Arrays.asList(
                        Earning.builder()
                                .type(PAY)
                                .value(BigDecimal.valueOf(2000.36)).build(),
                        Earning.builder()
                                .type(PRIME)
                                .value(BigDecimal.valueOf(1500.00)).build(),
                        Earning.builder()
                                .type(STOCK_OPTION)
                                .value(BigDecimal.valueOf(5000.69)).build()
                ), true)
        );
    }

    @Test
    public void testComputeCeciTax() {
        double expectedTax = (10000.00 + 7000.99 + 20000.36 + 15000.00) * 0.6;
        double actualTotalIncome = computeTotalIncome(employeesList);
        double actualTax = computeCeciTax(actualTotalIncome);
        assertEquals(expectedTax, actualTax);
    }

}
