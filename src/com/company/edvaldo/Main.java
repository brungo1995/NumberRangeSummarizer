package com.company.edvaldo;

import numberrangesummarizer.NumberRangeSummarizer;
import numberrangesummarizer.Summarizer;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
//        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31,-1,-2,0";
//        String input2 = "31,12,24,21,1,3,6,7,8,13,14,15,22,23,31,1,0,7,13,31";
//        String input = "-1,-2,0,1,3,2,4,0";
//        String input = "9,8,7,3,1,2,0,5";
        NumberRangeSummarizer summarizer = new Summarizer();
        Collection<Integer> numbers = summarizer.collect(input);
        String result = summarizer.summarizeCollection(numbers);
        System.out.println(result);
    }
}
