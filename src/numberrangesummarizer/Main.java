package numberrangesummarizer;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        try{
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
            Summarizer summarizer = new Summarizer();
            Collection<Integer> numbers = summarizer.collect(input);
            String result = summarizer.summarizeCollection(numbers);
            System.out.println(result);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
