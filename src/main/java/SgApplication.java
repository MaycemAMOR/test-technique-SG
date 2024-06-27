import com.sg.ceci.model.Earning;
import com.sg.ceci.model.Employee;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.sg.ceci.model.Type.*;
import static com.sg.ceci.service.ServiceEmployee.*;

public class SgApplication {

    public static void main(String[] args) {

        // Sample data
        List<Earning> earnings1 = Arrays.asList(
                Earning.builder()
                        .type(PAY)
                        .value(BigDecimal.valueOf(10000.00)).build(),
                Earning.builder()
                        .type(PRIME)
                        .value(BigDecimal.valueOf(70000.99)).build(),
                Earning.builder()
                        .type(RTT)
                        .value(BigDecimal.valueOf(20000.36)).build()
        );

        List<Earning> earnings2 = Arrays.asList(
                Earning.builder()
                        .type(PAY)
                        .value(BigDecimal.valueOf(15000.00)).build(),
                Earning.builder()
                        .type(PRIME)
                        .value(BigDecimal.valueOf(79000.99)).build(),
                Earning.builder()
                        .type(STOCK_OPTION)
                        .value(BigDecimal.valueOf(20000.36)).build()
        );

        List<Earning> earnings3 = Arrays.asList(
                Earning.builder()
                        .type(PAY)
                        .value(BigDecimal.valueOf(1000.00)).build(),
                Earning.builder()
                        .type(PRIME)
                        .value(BigDecimal.valueOf(700.99)).build(),
                Earning.builder()
                        .type(STOCK_OPTION)
                        .value(BigDecimal.valueOf(20.36)).build()
        );

        Employee employee1 = Employee.builder().earnings(earnings1).intern(false).build();
        Employee employee2 = Employee.builder().earnings(earnings2).intern(false).build();
        Employee employee3 = Employee.builder().earnings(earnings3).intern(true).build();

        List<Employee> employeesList = Arrays.asList(employee1, employee2, employee3);

        double totalIncome = computeTotalIncomeDouble(employeesList);
        double ceciTax = computeCeciTax(totalIncome);
        BigDecimal totalIncomeBigDecimal = computeTotalIncomeBigDecimal(employeesList);
        BigDecimal ceciTaxBigDecimal = computeCeciTax(totalIncomeBigDecimal);

        System.out.println("The amount of the CECI Tax: calculated with double type : " + ceciTax);
        System.out.println("The amount of the CECI Tax: calculated with BigDecimal type : " + ceciTaxBigDecimal);
    }


}
