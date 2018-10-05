package com.basejava.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainLambdaExamples {
    public static void main(String[] args) {
        //HW12 - i.
        int count = 10;
        int[] values =  new Random().ints(1,9)
                .limit(count).toArray();
        System.out.print("Original int array: {");
        for (int el : values) {
            System.out.print(el + " ");
        }
        System.out.println("}");
        System.out.print("minValues: ");
        System.out.println(minValue(values) + "\n");

        //HW12 - ii.
        int[] array = new Random().ints(1, 300).limit(count).toArray();
        List<Integer> list = new ArrayList<>();
        System.out.print("Original int list: {");
        int sum = 0;
        for (Integer el : array) {
            list.add(el);
            sum += el;
            System.out.print(el + " ");
        }
        System.out.print("}");
        System.out.println(" , sum of the elements " + sum + " is "  + (sum % 2 == 0 ? "even" : "odd"));
        System.out.print("Modified int list: {");
        list = oddOrEven(list);
        for (int el : list) {
            System.out.print(el + " ");
        }
        System.out.println("}");

    }

    private static int minValue(int[] values) {
        Set<Integer> set = Arrays.stream(values)
                .distinct().boxed().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        int count = set.size(), value = 0;
        for(Integer el : set) {
            value += el * ((Double) Math.pow(10, --count)).intValue();
        }
        return value;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        boolean flag = integers.stream().mapToInt(i -> i).sum() % 2 == 0;
        return integers.stream()
                .filter(el -> flag == (el % 2 != 0))
                .collect(Collectors.toList());
    }


}
