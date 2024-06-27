package com.sg.ceci.service;

import com.sg.ceci.model.Earning;
import com.sg.ceci.model.Employee;
import com.sg.ceci.model.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static com.sg.ceci.model.Type.*;

public class ServiceEmployee {
    static Set<Type> validTypes = Set.of(PAY, PRIME, RTT);
    static BigDecimal threshold = BigDecimal.valueOf(2.5).multiply(BigDecimal.valueOf(1014));

    public static double computeTotalIncomeDouble(List<Employee> employeesList) {
        return employeesList.stream()
                .filter(employee -> !employee.isIntern()) // Filter non-intern employees
                .mapToDouble(getEmployeeToDoubleFunction(validTypes)) // Sum the filtered earnings
                .filter(employeeEarnings -> employeeEarnings > 2.5 * 1014) // Only include if sum > 2.5*1014
                .sum(); // Sum all valid employee earnings
    }

    private static ToDoubleFunction<Employee> getEmployeeToDoubleFunction(Set<Type> validTypes) {
        return employee -> employee.getEarnings().stream()
                .filter(earning -> validTypes.contains(earning.getType()))// Filter PAY, Prime, and RTT earnings
                .map(Earning::getValue)
                .mapToDouble(BigDecimal::doubleValue) // Convert BigDecimal to double
                .sum();
    }


    public static BigDecimal computeTotalIncomeBigDecimal(List<Employee> employeesList) {
        return employeesList.stream()
                .filter(employee -> !employee.isIntern()) // Filter non-intern employees
                .map(getEmployeeBigDecimalFunction()) // Sum the filtered earnings
                .filter(employeeEarnings -> employeeEarnings.compareTo(threshold) > 0) // Only include if sum > 2.5 * 1014
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Sum all valid employee earnings
    }

    private static Function<Employee, BigDecimal> getEmployeeBigDecimalFunction() {
        return employee -> employee.getEarnings().stream()
                .filter(earning -> validTypes.contains(earning.getType())) // Filter PAY, Prime, and RTT earnings
                .map(Earning::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static double computeCeciTax(double totalIncome) {
        return totalIncome * 0.6;
    }

    public static BigDecimal computeCeciTax(BigDecimal totalIncome) {
        return totalIncome.multiply(BigDecimal.valueOf(0.6));
    }
}
