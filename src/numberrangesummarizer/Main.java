package numberrangesummarizer;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        try{
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
            CommaDelimitedSummarizer commaDelimitedSummarizer = new CommaDelimitedSummarizer();
            Collection<Integer> numbers = commaDelimitedSummarizer.collect(input);
            String result = commaDelimitedSummarizer.summarizeCollection(numbers);
            System.out.println(result);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
