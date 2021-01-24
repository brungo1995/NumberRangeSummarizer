package numberrangesummarizer;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
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


//    summarizeCollection

    /**
     * When the function summarizeCollection is called with a null value,
     * the validation method throws a NullPointerException,
     * which the function caller should handle
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledWithNull()  {
        Throwable event = null;
        String expected = "Collection cannot be null";
        String actual = "";

        try {
            Summarizer summarizer = new Summarizer();
            summarizer.summarizeCollection(null);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof NullPointerException);
        assertEquals(expected, actual);
    }


    /**
     * When the function summarizeCollection is called with an Empty Collection,
     * the validation method throws a InvalidInputException,
     * which the function caller should handle
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledWithAnEmptyCollection()  {
        Throwable event = null;
        String expected = "Collection cannot be empty";
        String actual = "";

        try {
            Summarizer summarizer = new Summarizer();
            Collection<Integer> input = Arrays.asList();
            summarizer.summarizeCollection(input);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof InvalidInputException);
        assertEquals(expected, actual);
    }


    /**
     * When the function summarizeCollection is called with a Collection
     * which has one or more values null = null
     * the validation method throws a NullPointerException
     * which the function caller should handle
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledWithOneNullValue()  {
        Throwable event = null;
        String expected = "One of the values in the collection is null";
        String actual = "";

        try {
            Summarizer summarizer = new Summarizer();
            Collection<Integer> input = new ArrayList<>();
            input.add(1);
            input.add(2);
            input.add(null);
            input.add(3);

            summarizer.summarizeCollection(input);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof NullPointerException);
        assertEquals(expected, actual);
    }

    /**
     * When the function summarizeCollection is called with only negatives numbers,
     * the result should be a string with only negative ranges (ascending order)
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledOnlyWithNegativeValues() throws InvalidInputException {
        String expected = "-10, -8--5, -2--1";
        Collection<Integer> input = Arrays.asList(-6, -1, -5, -2, -7,  -10, -8);
        Summarizer summarizer = new Summarizer();
        String  actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    /**
     * When the function summarizeCollection is called with only positive numbers,
     * the result should be a string with only positive ranges (ascending order)
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledOnlyWithPositiveValues() throws InvalidInputException {
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        Collection<Integer> input = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        Summarizer summarizer = new Summarizer();
        String  actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    /**
     * When the function summarizeCollection is called with  duplicate numbers,
     * the result should be a range string without duplicates
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledWithDuplicateValues() throws InvalidInputException {
        String expected = "2-3, 5-6, 10, 15-16, 31-32";
        Collection<Integer> input = Arrays.asList(10, 15, 5, 10, 16, 31, 6, 15,5, 32, 3 , 3, 2);
        Summarizer summarizer = new Summarizer();
        String  actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    /**
     * When the function summarizeCollection is called with positive and negative numbers,
     * the result should be a range starting with negatives and then positive ranges
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledWithPositiveAndNegateValues() throws InvalidInputException {
        String expected = "-8--6, -4, -2--1, 2-3, 5-7";
        Collection<Integer> input = Arrays.asList(-8,5, -1,7, -4, -7,3, 6,2, -2,-6);
        Summarizer summarizer = new Summarizer();
        String  actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    /**
     * When the function summarizeCollection is called with a unic range from negative to positives,
     * the result should be a range starting with negatives and then positive ranges
     */
    @Test
    public void testWhenSummarizeCollectionIsCalledWithUnicRange() throws InvalidInputException {
        String expected = "-2-5";
        Collection<Integer> input = Arrays.asList(5, 1, -1, 0, 2, -2, 4, 3);
        Summarizer summarizer = new Summarizer();
        String  actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }


    /**
     * Both functions collect and summarizeCollection must be called when creating
     * a collection and convert to a range string
     * The result is expected to be sorted ranges
     */
    @Test
    public void testThatCollectAndSummarizeCollectionAreIsCalled() throws InvalidInputException {
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        Summarizer summarizer = new Summarizer();
        Collection<Integer> input = summarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String  actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

}
