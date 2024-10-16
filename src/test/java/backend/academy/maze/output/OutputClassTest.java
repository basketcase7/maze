package backend.academy.maze.output;

import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static backend.academy.maze.output.OutputClass.getChooseA;
import static backend.academy.maze.output.OutputClass.getChooseB;
import static backend.academy.maze.output.OutputClass.printSmth;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@DisplayName("Testing the OutputClass")
public class OutputClassTest {

    @Test
    @DisplayName("Checking the message output")
    public void testPrintSmth() {
        PrintStream mockPrintStream = Mockito.mock(PrintStream.class);
        OutputClass.output = mockPrintStream;

        String out = "Smth";

        printSmth(out);

        verify(mockPrintStream).println(out);
    }

    @Test
    @DisplayName("Checking the output of the point A selection message")
    public void testGetChooseA() {
        int height = 15;
        int width = 10;

        String str = getChooseA(height, width);

        assertTrue(str.contains("Select the starting coordinate \"A\""));
        assertTrue(str.contains(String.format("height from 2 to %d", height)));
        assertTrue(str.contains(String.format("width from 2 to %d", width)));
    }

    @Test
    @DisplayName("Checking the output of the point B selection message")
    public void testGetChooseB() {
        int height = 15;
        int width = 10;

        String str = getChooseB(height, width);

        assertTrue(str.contains("Select the ending coordinate \"B\""));
        assertTrue(str.contains(String.format("height from 2 to %d", height)));
        assertTrue(str.contains(String.format("width from 2 to %d", width)));
    }
}
