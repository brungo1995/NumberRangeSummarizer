package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Summarizer implements  NumberRangeSummarizer{

    @Override
    public Collection<Integer> collect(String input) {
        // FIRST THING MUST BE VALIDATION AND PREPARATION OF the INPUT
//        if(!isValidInput()){
//
//        }
//         input = validateInput(input);
        // adding the numbers into a SET removes the need to check again for duplicates as SETS don't store duplicates
        Collection<Integer> numbers = new HashSet<>();

        Stream.of(input.split(","))
                .map(Integer::parseInt)
                .sorted()
                .forEach(numbers::add);

        return  numbers;
    }

    private boolean isValidInput(String string){
        // check if string is made of numbers (if they are parsable)

        // if there are special characters on the string you should remove then and only return the numeric items

        // check if values are comma delimited

        // check for decimal numbers

        // empty input

        // if null

        return false;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        Map<Integer, Integer> numberRanges = getRangesAsKeyValuePair(input);
        return prepareFinalOutput(numberRanges);
    }

    private Map<Integer,Integer> getRangesAsKeyValuePair(Collection<Integer> values){
        ArrayList<Integer> newList= new ArrayList<>(values);
        Map<Integer, Integer> map = new HashMap<>();

        int rangeStart = 0;
        boolean hasRangeStart = false;

        for (int i = 0; i < newList.size(); i++) {
            int nextNum ;
            int targetSum = newList.get(i) + 1;

//            Last item
            nextNum = i + 1 == newList.size() ? newList.get(newList.size() -1) : newList.get(i + 1);

            if(nextNum == targetSum && hasRangeStart){
                continue;
            }

            if (nextNum == targetSum && !hasRangeStart){
                rangeStart = newList.get(i);
                hasRangeStart = true;
                map.put(newList.get(i), null);
                continue;
            }

            if(nextNum != targetSum && hasRangeStart){
                //update the value of the map based on the key which is the start range
                hasRangeStart = false;
                map.replace(rangeStart, newList.get(i));
                continue;
            }

            if(nextNum != targetSum && !hasRangeStart){
                // in this case we don't have a range start neither the sum != current
                map.put(newList.get(i), null);
            }

        }

        return  map;
    }

    private String prepareFinalOutput(Map<Integer,Integer> numberRanges){

        String result = numberRanges.entrySet()
                            .stream()
                            .sorted(Map.Entry.comparingByKey())
                            .map(entry -> {
                            String range = "";

                            // check if this entry has a value, if it has a value, then it is a range
                            // key = start, value = end
                            if(entry.getValue() != null){
                                range = range.concat(String.valueOf(entry.getKey()));
                                range = range.concat("-");
                                range = range.concat(String.valueOf(entry.getValue()));
                            }else {
                                // if the entry has no value but has a key, then there is no continuity in the range
                                range = range.concat(String.valueOf(entry.getKey()));
                            }
                            return range;
                        })
                        .collect(Collectors.joining(", "));

        return result;
    }
}
