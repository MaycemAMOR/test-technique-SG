package com.sg.ceci.service;

import com.sg.ceci.model.Employees;
import com.sg.ceci.model.Revenue;
import com.sg.ceci.model.Type;

import java.math.BigDecimal;
import java.util.List;

public class CalculateTotalRevenue {
    public static double calculateTotalRevenue(List<Employees> employeesList) {
        return employeesList.stream()
                .filter(employee -> !employee.isIntern()) // Filter non-intern employees
                .mapToDouble(employee -> employee.getRevenue().stream()
                        .filter(revenue -> revenue.getType() == Type.PAY || revenue.getType() == Type.PRIME || revenue.getType() == Type.RTT) // Filter PAY, Prime, and RTT revenues
                        .map(Revenue::getValue)
                        .mapToDouble(BigDecimal::doubleValue) // Convert BigDecimal to double
                        .sum()) // Sum the filtered revenues
                .filter(employeeRevenue -> employeeRevenue > 2.5 * 1014) // Only include if sum > 2.5*1014
                .sum(); // Sum all valid employee revenues
    }

    public static double calculateCeciTax(double totalRevenue) {
        return totalRevenue * 0.6;
    }
}
