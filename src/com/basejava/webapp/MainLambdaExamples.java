package com.basejava.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class MainLambdaExamples {
    public static void main(String[] args) {
        //HW12 - i.
        int count = 6;
        int[] values = new Random().ints(1, 9)
                .limit(count).toArray();
        System.out.println(stream(values).boxed().map(String::valueOf)
                .collect(Collectors.joining(", ", "Original int array: {", "}")));
        System.out.print("minValues: " + minValue(values) + "\n");

        //HW12 - ii.
        int[] array = new Random().ints(1, 300).limit(count).toArray();
        List<Integer> list = Arrays.stream(array).boxed().collect(toList());
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println(list.stream().map(String::valueOf)
                .collect(Collectors.joining(", ", "Original int list: {", "} , sum of the elements " + sum + " is " + (sum % 2 == 0 ? "even" : "odd"))));
        System.out.println(oddOrEven(list).stream().map(String::valueOf)
                .collect(Collectors.joining(", ", "Modified int list: {", "}")));
    }

    private static int minValue(int[] values) {
        return stream(values)
                .distinct()
                .sorted()
                .reduce((a, b) -> b = b + a * 10).getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        int[] sum = {0};
        Predicate<Integer> isEven = i -> i % 2 == 0;
        return integers.stream().filter(el -> {
            sum[0] = sum[0] + el;
            return true;
        }).collect(toList()).stream()
                .filter(el -> {
                    if (isEven.test(sum[0])) {
                        return !isEven.test(el);
                    } else return isEven.test(el);
                })
                .collect(toList());
    }

}
