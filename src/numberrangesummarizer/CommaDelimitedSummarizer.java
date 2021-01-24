package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommaDelimitedSummarizer implements  NumberRangeSummarizer{
    private String ranges = "";

    @Override
    public Collection<Integer> collect(String input) throws InvalidInputException {
        Collection<Integer> sortedNumbers;
        input = validateInput(input);
        input = removeNonNumericCharacters(input);
        sortedNumbers = sortNumbersAndCreateCollection(input);
        return  sortedNumbers;
    }

    private String validateInput(String input) throws InvalidInputException {
        if(input == null)
            throw new NullPointerException("Invalid input. Please do not pass a null string");

        if(input.isEmpty())
            throw new InvalidInputException("Invalid input. Please do not pass an empty string");

        return input;
    }

    private String removeNonNumericCharacters(String input){
        input = input.replaceAll("[^\\d,.-]", "")
                .trim()
                .replaceAll(",-,",",");

        return input;
    }

    private Collection<Integer> sortNumbersAndCreateCollection(String formattedInput){

        return Stream.of(formattedInput.split(","))
                .filter(item -> isNumeric(item)
                                && !item.isEmpty()
                                && !item.isBlank()
                                && !item.contains(".")
                                && !item.equals("-"))
                .map(String::trim)
                .map(Integer::parseInt)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) throws InvalidInputException {
        input = validateAndCleanCollectionInput(input);
        String rangesFound = findRanges(input);
        setRanges(rangesFound);
        return getRanges();
    }

    private Collection<Integer> validateAndCleanCollectionInput(Collection<Integer> input) throws InvalidInputException {
        if(input == null)
            throw new NullPointerException("Collection cannot be null");

        if(input.isEmpty())
            throw new InvalidInputException("Collection cannot be empty");

        return input.stream()
                .filter(number -> number != null && isNumeric(Integer.toString(number)))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private String findRanges(Collection<Integer> masterColl){
        ArrayList<Integer> values= new ArrayList<>(masterColl);
        String output ="";
        boolean isSequential = false;
        int rangeStart = 0;
        int rangeEnd = 0;

        for (int i = 0; i < values.size(); i++) {
            boolean isLast = i + 1 == values.size();

            int nextNum ;
            int targetSum = values.get(i)  + 1;
            int currentValue = values.get(i);

            nextNum = isLast ? values.get(values.size() -1) : values.get(i + 1);

            if(nextNum == targetSum && isSequential){
                continue;
            }

            if (nextNum == targetSum && !isSequential){
                rangeStart = currentValue;
                isSequential = true;
                continue;
            }

            if(nextNum != targetSum && isSequential){
                isSequential = false;
                rangeEnd = currentValue;

                output = output.concat(String.valueOf(rangeStart ))
                        .concat("-")
                        .concat(String.valueOf(rangeEnd ));

                if(!isLast)
                    output = output.concat(", ");

                rangeStart = 0;

                continue;
            }

            if(nextNum != targetSum && !isSequential){
                output = output.concat(String.valueOf(currentValue ));

                if(!isLast)
                    output = output.concat(", ");
            }

        }

        return  output;
    }

    private String getRanges(){
        return ranges;
    }

    private void  setRanges(String ranges){
        this.ranges = ranges;
    }

}
