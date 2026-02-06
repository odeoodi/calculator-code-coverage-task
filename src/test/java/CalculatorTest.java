// java.laskin;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
/*
 * JUnit 5
 */

public class CalculatorTest { // The class name must end with Test

    // Fixture: all tests use the same calculator,
    // which is reset before each test.
    private Calculator calculator = new Calculator();
    private final double DELTA = 0.001;

    @BeforeEach
    public void clearCalculator() {
        calculator.reset();
    }

    // The test method name can be anything, as long as it has the @Test annotation
    @Test
    public void testAdd() {
        calculator.add(1);
        calculator.add(2);
        assertEquals(3, calculator.getResult());
    }

    @Test
    public void testAddDouble() {
        calculator.add(1.5);
        calculator.add(2.55);
        assertEquals(4.05, calculator.getResult(), DELTA);
    }

    @Test
    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(2);
        assertEquals(8, calculator.getResult());
    }

    @Test
    @DisplayName("Test division 8 / 2")
    public void testDivide() {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult());
    }

    @Test
    @DisplayName("Test double division: 8 / 3")
    public void testDoubleDivide() {
        calculator.add(8);
        calculator.divide(3);
        assertEquals(2.667, calculator.getResult(), DELTA);
    }
    // The correct result of this test is that division by zero throws an exception,
    // the caller then handles it in the desired way
    @Test
    @DisplayName("Test division by zero")
    public void testDivideByZero() {
        ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculator.divide(0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    public void testMultiply() {
        calculator.add(4);
        calculator.multiply(2);
        assertEquals(8, calculator.getResult());
    }

    @Test
    @DisplayName("Test multiply by doubles 8 * 1.3")
    public void testMultiply8By1point3() {
        calculator.add(8);
        calculator.multiply(1.3);
        assertEquals(10.4, calculator.getResult(), DELTA);
    }
}
