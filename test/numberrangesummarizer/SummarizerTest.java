package numberrangesummarizer;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class SummarizerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    /**
     * When the function collect is called with a null value,
     * the validation method throws a NullPointerException,
     * which the function caller should handle
     */
    @Test
    public void testWhenStringInputIsNull()  {
        Throwable event = null;
        String expected = "Invalid input. Please do not pass a null string";
        String actual = "";

        try {
            Summarizer summarizer = new Summarizer();
            summarizer.collect(null);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof NullPointerException);
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with an empty string,
     * the validation method throws an InvalidInputException,
     * which the function caller should handle
     */
    @Test
    public void testWhenStringInputIsEmpty(){
        Throwable event = null;
        String expected = "Invalid input. Please do not pass an empty string";
        String actual = "";

        try {
            Summarizer foo = new Summarizer();
            foo.collect("");

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof InvalidInputException);
        assertEquals(expected, actual);
    }


    /**
     * When the function collect is called with only negatives numbers,
     * the result should be a collection, that sorts the numbers from lowest to highest
     */
    @Test
    public void testWhenStringInputIsOnlyNegateNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(-10, -9, -8, -7, -6,-5, -4, -3, -2, -1);
        Summarizer summarizer = new Summarizer();
        Collection<Integer>  actual = summarizer.collect("-6, -9, -1, -5, -2, -7, -4, -3, -10, -8");
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with only positive numbers,
     * the result should be a collection, that sorts the numbers from lowest to highest
     */
    @Test
    public void testWhenStringInputIsOnlyPositiveNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        Summarizer summarizer = new Summarizer();
        Collection<Integer>  actual = summarizer.collect("31,3,8,24,1,23,7,22,6,8,21,15,12,14,13");
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with negative and positive numbers,
     * the result should be a collection, that sorts the numbers from lowest to highest
     */
    @Test
    public void testWhenStringInputHasNegativeAndPositiveNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(-10, -9, -8, -7, -6,-5, -4, -3, -2, -1,0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Summarizer summarizer = new Summarizer();
        Collection<Integer>  actual = summarizer.collect("10, 1, -4, -3, 8, 5,  -5, -2, -7, 4, 3, 9,0, 2, 7, 6,-6, -9, -1, -10, -8");
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with duplicate numbers,
     * the result should be a collection, that removes duplicates
     */
    @Test
    public void testWhenStringInputHasDuplicates() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(-5, -4, -3, -2, -1,0,1, 2, 3, 4, 5);
        Summarizer summarizer = new Summarizer();
        Collection<Integer>  actual = summarizer.collect("0,-1,5,-3,0,1,-4,-3,5,-5,-2,4,-2,3,0,2,-1,2");
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with a string which has non-numeric characters,
     * the function should continue by eliminating this characters
     * and return a valid collection.
     *
     * I decided to allow the code to still run because, the user can make very small
     * mistakes like extra comma or so, and to make the code a bit more more robust
     * and not stop execution so easily
     */
    @Test
    public void testWhenStringInputHasNoNumericCharacters() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList( -2, -1,0, 2, 3);
        Summarizer summarizer = new Summarizer();
        String nonNumericInput = "1..*1, !-3-, @3(),-, #-2R /  /,  $[ 0] ,  % - 1 % , ^2&, nul, ABCD,,,,,";
        Collection<Integer>  actual = summarizer.collect(nonNumericInput);
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with a string which has only non-numeric characters,
     * the function should return an empty List
     */
    @Test
    public void testWhenStringInputHasOnlyNoNumericCharacters() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList();
        Summarizer summarizer = new Summarizer();
        String nonNumericInput = "!,@,#,$,A,B,BNT,+{},^,";
        Collection<Integer>  actual = summarizer.collect(nonNumericInput);
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with a string which has extra commas,
     * the function should return a sorted list
     */
    @Test
    public void testWhenStringInputHasExtraCommas() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList( -3,-2, -1,0,1, 2, 3);
        Summarizer summarizer = new Summarizer();
        String nonNumericInput = ",,,-1,,,2,,-3,,,0,,,3,,,,-2,,,,1,,,,";
        Collection<Integer>  actual = summarizer.collect(nonNumericInput);
        assertEquals(expected, actual);
    }

    /**
     * When the function collect is called with fractional numbers,
     * the result should be a collection without fractions only decimal numbers
     * (incl. negatives)
     */
    @Test
    public void testWhenStringInputHasFractionalNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList( -4, -3,0,1, 2, 4);
        Summarizer summarizer = new Summarizer();
        Collection<Integer>  actual = summarizer.collect("-1.3, -4, 0.999, 1, 4, -3, 0, 2, -2.5");
        assertEquals(expected, actual);
    }


//    summarizeCollection

    

//    Collect and summarizeCollection tests
    // TODO: 2021/01/24  test with not properly fomated numbers ",,,,,*(*& 012"
    // TODO: 2021/01/24 test when some numbers inside collection is are null
    // TODO: 2021/01/24 with decimal numbers
    // TODO: 2021/01/24 if function collect is called
    // TODO: 2021/01/24 if  summarize is called
    // TODO: 2021/01/24 duplicates negative and posive

}
