package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Summarizer implements  NumberRangeSummarizer{
    private String ranges = "";

    @Override
    public Collection<Integer> collect(String input) throws InvalidInputException {
        Collection sortedNumbers;
        input = validateInput(input);
        input = removeNonNumericCharacters(input);
        sortedNumbers = sortNumbersAndCreateCollection(input);

        return  sortedNumbers;
    }

    private String validateInput(String input) throws InvalidInputException {
        // if null
        if(input == null)
            throw new NullPointerException("User passed null input");

        // empty input
        if(input.isEmpty())
            throw new InvalidInputException("User passed empty input");

        //if not string
        if(!(input instanceof  String)){
            throw new NumberFormatException("User passed invalid input format. Input should be a String");
        }

        // remove all non numeric character excluding commas
//        input = removeNonNumericCharacters(input);

        return input;
    }

    private String removeNonNumericCharacters(String input){
        // remove special all special characters and only leaves numeric values (including negative numbers)
        // input = input.replaceAll("[^0-9,.]", "");
//        input = input.replaceAll("[^\\d,.-]", "");
        input = input.replaceAll("[^\\d,-]", "");

//        // removes spaces, fractional numbers, and blanks
//        input = Stream.of(input.split(","))
//                .filter(item -> !item.isEmpty()
//                        && !item.isBlank()) //filter out decimal values
//                .map(String::trim)
//                .map(Integer::parseInt)
//                .distinct() // removes duplicates
//                .sorted()
//                .map(Object::toString) //convert back to string
//                .collect(Collectors.joining(",")); // join everything into a single string
        return input;
    }

    private Collection<Integer> sortNumbersAndCreateCollection(String formattedInput){
        Collection<Integer> sortedNumbers = new ArrayList<Integer>();

        Stream.of(formattedInput.split(","))
                .filter(item -> !item.isEmpty()
                        && !item.isBlank()) //filter out decimal values
                .map(String::trim)
                .map(Integer::parseInt)
                .distinct() // removes duplicates
                .sorted()
                .forEach(sortedNumbers::add);

                return sortedNumbers;
    }
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        String rangesFound = findRanges(input);
        setRanges(rangesFound);
        return getRanges();
    }

    private String findRanges(Collection<Integer> masterColl){
        ArrayList<Integer> values= new ArrayList<>(masterColl);
        String output ="";
        boolean isSequential = false;
        int rangeStart = 0;
        int rangeEnd = 0;

        for (int i = 0; i < values.size(); i++) {
            int nextNum ;
            int targetSum = values.get(i) + 1;
            int currentValue = values.get(i);

            // Last item
            nextNum = i + 1 == values.size() ? values.get(values.size() -1) : values.get(i + 1);

            if(nextNum == targetSum && isSequential){
                continue;
            }

            //add range start and the next sum is sequential
            if (nextNum == targetSum && !isSequential){
                rangeStart = currentValue;
                isSequential = true;
                continue;
            }

            // reached range end, next number is not sequential
            if(nextNum != targetSum && isSequential){
                //update the value of the map based on the key which is the start range
                isSequential = false;
                rangeEnd = currentValue;

                // add the range to the output variable
                output = output.concat(String.valueOf(rangeStart ))
                        .concat("-")
                        .concat(String.valueOf(rangeEnd ));

                // if it is not the last item add "," to the output variable
                if(!isLast(i + 1,  values.size()))
                    output = output.concat(", ");

                rangeStart = 0;
                rangeEnd = 0;

                continue;
            }

            // in this case the next number is not sequential
            if(nextNum != targetSum && !isSequential){
                // in this case we don't have a range start neither the sum != current
                output = output.concat(String.valueOf(currentValue ));

                // if it is not the last item add "," to the output variable
                if(!isLast(i + 1,  values.size()))
                    output = output.concat(", ");
            }

        }

        return  output;
    }

    private boolean isLast(Integer currentIndex, Integer arrSize){
        return  currentIndex == arrSize;
    }

    private String getRanges(){
        return ranges;
    }

    private void  setRanges(String ranges){
        this.ranges = ranges;
    }

}
