import com.sg.ceci.model.Employees;
import com.sg.ceci.model.Revenue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sg.ceci.model.Type.*;
import static com.sg.ceci.service.CalculateTotalRevenue.calculateCeciTax;
import static com.sg.ceci.service.CalculateTotalRevenue.calculateTotalRevenue;

public class SgApplication {

    public static void main(String[] args) {

        // Sample data
        List<Revenue> revenue1 = Arrays.asList(
                Revenue.builder()
                        .type(PAY)
                        .value(BigDecimal.valueOf(10000.00)).build(),
                Revenue.builder()
                        .type(PRIME)
                        .value(BigDecimal.valueOf(70000.99)).build(),
                Revenue.builder()
                        .type(RTT)
                        .value(BigDecimal.valueOf(20000.36)).build()
        );

        List<Revenue> revenue2 = Arrays.asList(
                Revenue.builder()
                        .type(PAY)
                        .value(BigDecimal.valueOf(15000.00)).build(),
                Revenue.builder()
                        .type(PRIME)
                        .value(BigDecimal.valueOf(79000.99)).build(),
                Revenue.builder()
                        .type(STOCK_OPTION)
                        .value(BigDecimal.valueOf(20000.36)).build()
        );

        List<Revenue> revenue3 = Arrays.asList(
                Revenue.builder()
                        .type(PAY)
                        .value(BigDecimal.valueOf(1000.00)).build(),
                Revenue.builder()
                        .type(PRIME)
                        .value(BigDecimal.valueOf(700.99)).build(),
                Revenue.builder()
                        .type(STOCK_OPTION)
                        .value(BigDecimal.valueOf(20.36)).build()
        );

        Employees employee1 = Employees.builder().revenue(revenue1).intern(false).build();
        Employees employee2 = Employees.builder().revenue(revenue2).intern(false).build();
        Employees employee3 = Employees.builder().revenue(revenue3).intern(true).build();

        List<Employees> employeesList = Arrays.asList(employee1, employee2, employee3);

        double totalRevenue = calculateTotalRevenue(employeesList);
        double ceciTax = calculateCeciTax(totalRevenue);

        System.out.println("The amount of the CECI Tax: " + ceciTax);
    }


}
