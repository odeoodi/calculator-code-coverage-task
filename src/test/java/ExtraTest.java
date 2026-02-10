
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExtraTest extends AbstractParent {

    private static Calculator calculator = new Calculator();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testPowerOn() {
        System.out.println("@BeforeAll Power ON (before the first test)");
        calculator.powerOn();
    }

    @AfterAll
    public static void testPowerOff() {
        System.out.println("@AfterAll Power OFF (all tests executed).");
        calculator.powerOff();
        calculator = null;
    }

    @BeforeEach
    public void testReset() {
        System.out.println("  Reset calculator.");
        calculator.reset();
        assertEquals(0, calculator.getResult(), "Reset failed");
    }

    @ParameterizedTest(name = "The square of {0} is {1}")
    @CsvSource({ "0, 0", "1, 1", "2, 4", "4, 16", "5, 25", "6, 36" })
    public void testSquare(int number, int result) {
        calculator.square(number);
        assertEquals(result, calculator.getResult(), "Squaring "+number+"  is incorrect");
    }

    @ParameterizedTest(name = "The square root of {0} is {1}")
    @CsvSource({ "0, 0", "1, 1", "2, 1.414", "4, 2", "16, 4", "25, 5" })
    public void testSquareRoot(int number, double result) {
        calculator.squareRoot(number);
        assertEquals(result, calculator.getResult(), DELTA, "Squaring root "+number+" is incorrect");
    }

    @Test
    @DisplayName("Test negative square root")
    public void testSquareRootNegative() {
        ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculator.squareRoot(-4));
        assertEquals("Cannot get square root of negative number", exception.getMessage());

    }
}
