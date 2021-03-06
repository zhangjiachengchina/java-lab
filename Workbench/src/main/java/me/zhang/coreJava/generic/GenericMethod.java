package me.zhang.coreJava.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhangxiangdong on 2017/11/13.
 */
public class GenericMethod {

    private static void printList(List<?> list) {
        duplicateList(list);
        System.out.println("Sum: " + list.size());
        for (Object o : list) {
            System.out.print(o);
            System.out.print(" ");
        }
        /* only null can be inserted */
        // list.add("a string"); // ERROR
        // list.add(null); // it works
        System.out.println();
    }

    private static void duplicateList(List<?> list) {
        duplicateListHelper(list);
    }

    private static <E> void duplicateListHelper(List<E> list) {
        /* duplicate list */
        int preSize = list.size();
        for (int i = 0; i < preSize; i++) {
            list.add(list.get(i));
        }
    }

    private static void addNumbers(List<? super Integer> list) {
        for (int i = 10; i < 100; i += 10) {
            list.add(i);
        }
    }

    private static void swapFirst(List<? extends Number> firstList, List<? extends Number> secondList) {
        // https://docs.oracle.com/javase/tutorial/java/generics/capture.html
        // There is no helper method to work around the problem, because the code is fundamentally wrong.
        // swapFirstHelper(firstList, secondList);
    }

//    private static <E extends Number> void swapFirstHelper(List<E> firstList, List<E> secondList) {
//        E temp = firstList.get(0);
//        firstList.set(0, secondList.get(0));
//        secondList.set(0, temp);
//    }

    public static void main(String[] args) {
        System.out.println(GenericMethod.<Integer>getMiddle(1, 2, 3));
        System.out.println(GenericMethod.getMiddle("A", "B", "C"));

        // double middle = getMiddle(3.14, 2, 1000000000000L);
        System.out.println(getMiddle(3.14, 2, 1000000000000L));

        System.out.println(min(2, 1, 4, 3, 7, 6, 5, 8, 9));
        System.out.println(min(3.1, 2.0, 4.3));

        // inspect("Hi"); // ERROR!!!
        inspect(2_000);

        Character[] characters = new Character[]{'a', 'b', 'c', 'd', 'e'};
        int count = countGreaterThan(characters, 'b');
        System.out.println(count); // 3

        // The common parent of Box<Integer> and Box<Number> is Object.
        // Box<Integer> iBox = new Box<>(123);
        // System.out.println(getNumber(iBox); // ERROR!!!
        System.out.println(getNumber(new Box<>(12.0)));

        List<String> emptyList = Collections.<String>emptyList();
        processStringList(emptyList);
        processStringList(Collections.emptyList()); // error on java 7: List<Object> cannot be converted to List<String>

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5); // fixed-size
        System.out.println(sumOfNumber(integers));

        List<Double> doubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        System.out.println(sumOfNumber(doubles));

        System.out.println("**************************");
        integers = new ArrayList<>(integers);
        printList(integers);
        printList(new ArrayList<>(doubles));

        addNumbers(integers);
        printList(integers);

        System.out.println("**************************");
        List<? extends Integer> iList = new ArrayList<>();
        iList.add(null);
        List<? extends Number> nList = iList; // OK. List<? extends Integer> is a subtype of List<? extends Number>
        nList.add(null);
        System.out.println(iList.size());
    }

    private static void processStringList(List<String> target) {

    }

    @SafeVarargs
    private static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    @SafeVarargs
    private static <T extends Number & Comparable<T>> T min(T... a) {
        if (a == null || a.length == 0) {
            return null;
        }

        T smallest = a[0];
        for (T t : a) {
            if (t.compareTo(smallest) < 0) {
                smallest = t;
            }
        }
        return smallest;
    }

    private static <T extends Number> void inspect(T input) {
        System.out.println(input);
    }

    private static <T extends Comparable<T>> int countGreaterThan(T[] ts, T t) {
        if (ts == null) {
            throw new NullPointerException();
        }
        int count = 0;
        for (T t1 : ts) {
            if (t1.compareTo(t) > 0) {
                count++;
            }
        }
        return count;
    }

    private static Number getNumber(Box<Number> nBox) {
        return nBox.getT();
    }

    private static double sumOfNumber(List<? extends Number> numbers) {
        double sum = 0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }

}
