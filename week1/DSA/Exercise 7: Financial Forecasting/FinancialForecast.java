import java.util.Scanner;

public class FinancialForecast {

    /*
    ** Recursion:
    Recursion is a technique where a method calls itself to solve smaller parts of a problem.
    In financial forecasting, we can use recursion to calculate compound interest:
    FV = PV × (1 + rate)^years
    Instead of using Math.pow(), we recursively multiply (1 + rate) each year.
    */

    // Recursive method to calculate future value
    public static double forecastFutureValue(double presentValue, double rate, int years) {
        if (years == 0) {
            return presentValue; // Base case: no more growth
        }
        return (1 + rate) * forecastFutureValue(presentValue, rate, years - 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input
        System.out.print("Enter present value: ");
        double presentValue = scanner.nextDouble();

        System.out.print("Enter annual growth rate (e.g., 0.05 for 5%): ");
        double annualRate = scanner.nextDouble();

        System.out.print("Enter number of years: ");
        int years = scanner.nextInt();

        // Call recursive forecast function
        double futureValue = forecastFutureValue(presentValue, annualRate, years);

        // Display result
        System.out.printf("Predicted Future Value after %d years: %.2f\n", years, futureValue);

        scanner.close();
    }

    /*
    **Time Complexity:
    - This recursive algorithm runs in O(n) time, where n = number of years.
    - It performs one recursive call per year until reaching 0.

    ** Optimization Note:
    - For very large 'years' values, recursion can cause a stack overflow.
    - Alternatives:
      → Use an iterative loop for better performance and stability.
      → Use memoization if intermediate results are reused (not needed here).
    */
}
