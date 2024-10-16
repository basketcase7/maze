package backend.academy.maze;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Testing the InputClass")
class InputClassTest {

    @Test
    @DisplayName("Checking the correct height input")
    void testInputHeightValid() {
        String validInput = "10";
        assertTrue(InputClass.inputHeight(validInput));
    }

    @Test
    @DisplayName("Checking the height input when entering less than the minimum")
    void testInputHeightInvalidBelowMinimum() {
        String invalidInput = "4";
        assertFalse(InputClass.inputHeight(invalidInput));
    }

    @Test
    @DisplayName("Checking the height input when entering more than the minimum")
    void testInputHeightInvalidAboveMaximum() {
        String invalidInput = "31";
        assertFalse(InputClass.inputHeight(invalidInput));
    }

    @Test
    @DisplayName("Checking height input when entering non-numbers")
    void testInputHeightInvalidNonNumeric() {
        String invalidInput = "abc";
        assertFalse(InputClass.inputHeight(invalidInput));
    }

    @Test
    @DisplayName("Checking the correct width input")
    void testInputWidthValid() {
        String validInput = "100";
        assertTrue(InputClass.inputWidth(validInput));
    }

    @Test
    @DisplayName("Checking the width input when entering less than the minimum")
    void testInputWidthInvalidBelowMinimum() {
        String invalidInput = "4";
        assertFalse(InputClass.inputWidth(invalidInput));
    }

    @Test
    @DisplayName("Checking the width input when entering more than the minimum")
    void testInputWidthInvalidAboveMaximum() {
        String invalidInput = "151";
        assertFalse(InputClass.inputWidth(invalidInput));
    }

    @Test
    @DisplayName("Checking width input when entering non-numbers")
    void testInputWidthInvalidNonNumeric() {
        String invalidInput = "xyz";
        assertFalse(InputClass.inputWidth(invalidInput));
    }

    @Test
    @DisplayName("Checking the correct algorithm input")
    void testInputAlgorithmValid() {
        String validInput = "1";
        assertTrue(InputClass.inputAlgorithm(validInput));
    }

    @Test
    @DisplayName("Checking the algorithm input when entering too large a value")
    void testInputAlgorithmInvalidOutsideRange() {
        String invalidInput = "3";
        assertFalse(InputClass.inputAlgorithm(invalidInput));
    }

    @Test
    @DisplayName("Checking algorithm input when entering non-numbers")
    void testInputAlgorithmInvalidNonNumeric() {
        String invalidInput = "foo";
        assertFalse(InputClass.inputAlgorithm(invalidInput));
    }

    @Test
    @DisplayName("Checking the input of a natural number")
    void testCheckInputValidValid() {
        assertTrue(InputClass.checkInputValid("123"));
        assertTrue(InputClass.checkInputValid("5"));
    }

    @Test
    @DisplayName("Checking the input of a non-natural number")
    void testCheckInputValidInvalid() {
        assertFalse(InputClass.checkInputValid("abc"));
        assertFalse(InputClass.checkInputValid("0"));
        assertFalse(InputClass.checkInputValid("-10"));
    }
}
