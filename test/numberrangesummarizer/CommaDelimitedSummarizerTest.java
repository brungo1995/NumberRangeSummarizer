package numberrangesummarizer;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class CommaDelimitedSummarizerTest {

    @Test
    public void testCollectWhenStringInputIsNull()  {
        Throwable event = null;
        String expected = "Invalid input. Please do not pass a null string";
        String actual = "";

        try {
            CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
            commaDelimitedSummarizer.collect(null);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof NullPointerException);
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputIsEmpty(){
        Throwable event = null;
        String expected = "Invalid input. Please do not pass an empty string";
        String actual = "";

        try {
            CommaDelimitedSummarizer foo = new CommaDelimitedSummarizer();
            foo.collect("");

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof InvalidInputException);
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputHasDuplicates() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(-5, -4, -3, -2, -1,0,1, 2, 3, 4, 5);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        Collection<Integer>  actual = commaDelimitedSummarizer.collect("0,-1,5,-3,0,1,-4,-3,5,-5,-2,4,-2,3,0,2,-1,2");
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputHasNonNumericCharacters() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList( -2, -1,0, 2, 3);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String nonNumericInput = "1..*1, !-3-, @3(),-, #-2R /  /,  $[ 0] ,  % - 1 % , ^2&, nul, ABCD,,,,,";
        Collection<Integer>  actual = commaDelimitedSummarizer.collect(nonNumericInput);
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputHasOnlyNonNumericCharacters() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList();
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String nonNumericInput = "!,@,#,$,A,B,BNT,+{},^,";
        Collection<Integer>  actual = commaDelimitedSummarizer.collect(nonNumericInput);
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputHasExtraCommas() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList( -3,-2, -1,0,1, 2, 3);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String nonNumericInput = ",,,-1,,,2,,-3,,,0,,,3,,,,-2,,,,1,,,,";
        Collection<Integer>  actual = commaDelimitedSummarizer.collect(nonNumericInput);
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputHasFractionalNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList( -4, -3,0,1, 2, 4);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        Collection<Integer>  actual = commaDelimitedSummarizer.collect("-1.3, -4, 0.999, 1, 4, -3, 0, 2, -2.5");
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputIsOnlyNegateNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(-10, -9, -8, -7, -6,-5, -4, -3, -2, -1);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        Collection<Integer>  actual = commaDelimitedSummarizer.collect("-6, -9, -1, -5, -2, -7, -4, -3, -10, -8");
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputIsOnlyPositiveNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        Collection<Integer>  actual = commaDelimitedSummarizer.collect("31,3,8,24,1,23,7,22,6,8,21,15,12,14,13");
        assertEquals(expected, actual);
    }

    @Test
    public void testCollectWhenStringInputHasNegativeAndPositiveNumbers() throws InvalidInputException {
        Collection<Integer> expected = Arrays.asList(-10, -9, -8, -7, -6,-5, -4, -3, -2, -1,0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        Collection<Integer>  actual = commaDelimitedSummarizer.collect("10, 1, -4, -3, 8, 5,  -5, -2, -7, 4, 3, 9,0, 2, 7, 6,-6, -9, -1, -10, -8");
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithNullCollection()  {
        Throwable event = null;
        String expected = "Collection cannot be null";
        String actual = "";

        try {
            CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
            commaDelimitedSummarizer.summarizeCollection(null);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof NullPointerException);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithAnEmptyCollection()  {
        Throwable event = null;
        String expected = "Collection cannot be empty";
        String actual = "";

        try {
            CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
            Collection<Integer> input = Arrays.asList();
            commaDelimitedSummarizer.summarizeCollection(input);

        } catch (Throwable ex) {
            event = ex;
            actual = event.getMessage();
        }

        assertTrue(event instanceof InvalidInputException);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithNullValues() throws InvalidInputException {
        String expected = "0, 2-3, 5-6";
        Collection<Integer> input = Arrays.asList(null, 0, 3, 5,null, 2, 6,  null);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledOnlyWithNegativeValues() throws InvalidInputException {
        String expected = "-10, -8--5, -2--1";
        Collection<Integer> input = Arrays.asList(-6, -1, -5, -2, -7,  -10, -8);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledOnlyWithPositiveValues() throws InvalidInputException {
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        Collection<Integer> input = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithDuplicateValues() throws InvalidInputException {
        String expected = "2-3, 5-6, 10, 15-16, 31-32";
        Collection<Integer> input = Arrays.asList(10, 15, 5, 10, 16, 31, 6, 15,5, 32, 3 , 3, 2);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithPositiveAndNegateValues() throws InvalidInputException {
        String expected = "-8--6, -4, -2--1, 2-3, 5-7";
        Collection<Integer> input = Arrays.asList(-8,5, -1,7, -4, -7,3, 6,2, -2,-6);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithASingleRange() throws InvalidInputException {
        String expected = "-2-5";
        Collection<Integer> input = Arrays.asList(5, 1, -1, 0, 2, -2, 4, 3);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithBigNumbers() throws InvalidInputException {
        String expected = "10000-10001, 25000-25001, 250000-250001, 366000-366001, 400536-400537, 9200500-9200501, 1000000000-1000000001";
        Collection<Integer> input = Arrays.asList(
                10000, 250000, 9200500, 25000, 366000, 400536, 1000000000, 250001,
                9200501, 366001, 400537, 1000000001, 10001, 25001);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testSummarizeCollectionWhenIsCalledWithUnOrderedNumbers() throws InvalidInputException {
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        Collection<Integer> input = Arrays.asList(
                 31, 15, 1, 13, 6, 3, 21, 12, 7, 24, 14, 8,22, 23);
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }


    @Test
    public void testThatCollectAndSummarizeCollectionAreIsCalled() throws InvalidInputException {
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
        Collection<Integer> input = commaDelimitedSummarizer.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String  actual = commaDelimitedSummarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

}
