package numberrangesummarizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Summarizer implements  NumberRangeSummarizer{

    @Override
    public Collection<Integer> collect(String input) throws InvalidInputException {
        // FIRST THING MUST BE VALIDATION AND PREPARATION OF the INPUT
        // IF WHAT COMES OUT OF THE VALIDATION FN IS NOT A PROPER INPUT RETURN AND PRINT THE MESSAGE
//        if(!isValidInput()){
//
//        }
        isValidInput(input);

//         input = validateInput(input);
        // adding the numbers into a SET removes the need to check again for duplicates as SETS don't store duplicates
        Collection<Integer> numbers = new HashSet<>();

//        Stream.of(input.split(","))
//                .map(Integer::parseInt)
//                .sorted()
//                .forEach(numbers::add);

//        removes duplicates without a map
//        List<Integer> withoutDupes = Stream.of(input.split(",")).stream()
//                .distinct()
//                .collect(Collectors.toList());

        return  numbers;

    }

    private boolean isValidInput(String input) throws InvalidInputException {

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

        // check if input has special characters
//        if(hasNonNumericValues(input)){
//            throw new NumberFormatException("User passed non numeric values. Please only use numbers followed by commas");
//        }

        // remove all non numeric character exclucing commas
        input = removeNonNumericCharacters(input);

        // at this point there might also still be invalid commas or invalid formatd numbers
        input = formatNumbers(input);

        System.out.println(input);
        // check if string is made of numbers (if they are parsable)
        // parseInt can throw a NumberFormatException
        // check if values are comma delimited
        // check for decimal numbers





        return false;
    }

    private String removeNonNumericCharacters(String input){
        return  input.replaceAll("[^0-9,.]", "");
    }

    private String formatNumbers(String input){
        List<Integer> list = new ArrayList<>();
//              List<Integer> splitInputs = Stream.of(input.split(","))
//                .filter(item -> !item.isEmpty()
//                                && !item.isBlank()
//                                && !item.contains("."))
//                .map(String::trim)
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());

             String splitInputs = Stream.of(input.split(","))
                .filter(item -> !item.isEmpty()
                                && !item.isBlank()
                                && !item.contains("."))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(Object::toString)
                .collect(Collectors.joining(","));


//        List<String> splitInputs = Stream.of(input.split(","))
//                .map(String::trim)
//                .collect(Collectors.toList());
//
//        for(String item: splitInputs){
//
//            if(item.isEmpty() || item.isBlank()){
//                continue;
//            }
//
//            if(item.contains(".")){
//                continue;
//            }
//
//            list.add(Integer.valueOf(item));
//        }


        return  splitInputs;
//        return  list.toString();
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
