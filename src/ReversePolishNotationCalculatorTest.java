import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReversePolishNotationCalculatorTest {

    static ReversePolishNotationCalculator reversePolishNotationCalculator = new ReversePolishNotationCalculator();

    @Test
    public void shouldCalculateAddition() {
        int s = reversePolishNotationCalculator.calculatePolishNotation("1 2 3 + +");
        assertEquals(6, s);
    }

    @Test
    public void shouldCalculateSubtraction() {
        int s = reversePolishNotationCalculator.calculatePolishNotation("1 2 3 - -");
        assertEquals(2, s);
    }

    @Test
    public void shouldCalculateMultiplication() {
        int s = reversePolishNotationCalculator.calculatePolishNotation("1 2 3 * *");
        assertEquals(6, s);
    }

    @Test
    public void shouldCalculateNegativeNumbers() {
        int s = reversePolishNotationCalculator.calculatePolishNotation("-1 -2 -3 + +");
        assertEquals(-6, s);
    }

    @Test
    public void shouldCalculateWithMoreThanOneSpaceInString() {
        int s = reversePolishNotationCalculator.calculatePolishNotation("-1     -2          -3 +        +");
        assertEquals(-6, s);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}