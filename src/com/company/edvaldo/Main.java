package com.company.edvaldo;

import numberrangesummarizer.NumberRangeSummarizer;
import numberrangesummarizer.Summarizer;

import java.util.ArrayList;
import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
//        String input = "1,3,6,7,8,12,13,14,-20,15,21,22,23,24,31,-1,-2,-3,0";
//        String input = "1,3,6,7,8,12,13,14,-20,15,21,22,23,24,31,-1,-2,-3,0";
//        String input = "-20,-1,-2,-3,0,5";
//        String input = ",-5,-2,-1,2,3";
//        String input = "20,19,-1,1,3,3,000,22,-20,-2,0,1,2";
//        String input2 = "31,12,24,21,1,3,6,7,8,13,14,15,22,23,31,1,0,7,13,31";
//        String input = "-1,-2,0,1,3,2,4,0";
//        String input = "9,8,7,3,1,2,0,5";

        //invalid inputs
//        String input = ",, ,1  ,2, 3,4.*,4.5.7";
//        String input = "     ,,,1, null"+ null + "2,3,     , , ,, .....,,,..***";
//        String input = "1abd0,,,,2,re,**,3";
//        String input = "10.6";
//        String input = "10.,6, ***, {1[,[2],*3,0.*5,,}[]";
//        String input = "  , , , ,,,1, 2, a,b,b,fggd,g,ergege,2......1,3.000,9";
//        String input = " 1,1,2,3,5,5";
//        String input = "";
//        String input = "ABC";
//        String input = null;
//        var input = new Object();
//        String input = "sgsfgs000000012,,,,        2.36,000000,98";
//        String input = "00000000001,20,......6,0.8,98";
//        String input = "1,3,4,5,002";

        try{
            Summarizer summarizer = new Summarizer();
//            Collection<Integer> numbers = summarizer.collect(input);
              Collection<Integer> numbers = null;
//            Collection<Integer> numbers = new ArrayList<>();
//            numbers.add(1);
//            numbers.add(null);
            String result = summarizer.summarizeCollection(numbers);
            System.out.println(result);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
//            System.out.println(ex.getCause());
        }

    }
}
