package legacy.forth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ForthEvaluatorDiffblueTest {
    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();
        ArrayList<String> commands = new ArrayList<>();

        // Act
        List<Integer> actualEvaluateProgramResult = forthEvaluator.evaluateProgram(commands);

        // Assert
        assertTrue(actualEvaluateProgramResult.isEmpty());
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram2() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("foo");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram3() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add(" ");

        // Act
        List<Integer> actualEvaluateProgramResult = forthEvaluator.evaluateProgram(commands);

        // Assert
        assertTrue(actualEvaluateProgramResult.isEmpty());
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram4() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add(" +");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram5() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("-");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram6() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("*");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram7() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("42");

        // Act
        List<Integer> actualEvaluateProgramResult = forthEvaluator.evaluateProgram(commands);

        // Assert
        assertEquals(1, actualEvaluateProgramResult.size());
        assertEquals(42, actualEvaluateProgramResult.get(0));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram8() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("/");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram9() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("dup");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram10() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("drop");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram11() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("swap");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram12() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("over");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }

    /**
     * Method under test: {@link ForthEvaluator#evaluateProgram(List)}
     */
    @Test
    void testEvaluateProgram13() {
        // Arrange
        ForthEvaluator forthEvaluator = new ForthEvaluator();

        ArrayList<String> commands = new ArrayList<>();
        commands.add("No definition available for operator \"%s\"");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> forthEvaluator.evaluateProgram(commands));
    }
}
