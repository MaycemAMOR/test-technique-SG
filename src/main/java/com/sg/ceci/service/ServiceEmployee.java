package com.sg.ceci.service;

import com.sg.ceci.model.Earning;
import com.sg.ceci.model.Employee;
import com.sg.ceci.model.Type;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ServiceEmployee {
    public static double computeTotalIncome(List<Employee> employeesList) {

        Set<Type> validTypes = Set.of(Type.PAY, Type.PRIME, Type.RTT);

        return employeesList.stream()
                .filter(employee -> !employee.isIntern()) // Filter non-intern employees
                .mapToDouble(employee -> employee.getEarnings().stream()
                        .filter(earning -> validTypes.contains(earning.getType()))// Filter PAY, Prime, and RTT earnings
                        .map(Earning::getValue)
                        .mapToDouble(BigDecimal::doubleValue) // Convert BigDecimal to double
                        .sum()) // Sum the filtered earnings
                .filter(employeeEarnings -> employeeEarnings > 2.5 * 1014) // Only include if sum > 2.5*1014
                .sum(); // Sum all valid employee earnings
    }

    public static double computeCeciTax(double totalIncome) {
        return totalIncome * 0.6;
    }
}
