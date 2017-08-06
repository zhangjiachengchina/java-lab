package me.zhang.string;

import java.util.Arrays;

/**
 * Created by Zhang on 8/6/2017 8:23 AM.
 */
public class StringOperator {

    // 创建一个随机字符串，随机字符有可能是数字，大写字母或者小写字母
    public void randomCharArray(char[] array) {
        // a-z(97-122) A-Z(65-90) 0-9(48-57)
        final int z = 122;
        final int zero = 48;
        int count = 0;
        while (count < array.length) {
            int random = (int) (zero + Math.random() * (z - zero + 1));
            if (Character.isDigit(random) || Character.isUpperCase(random)
                    || Character.isLowerCase(random)) {
                array[count] = (char) random;
                count++;
            }
        }
    }

    // 创建一个随机字符串数组，使用长度是5的随机字符串初始化这个数组
    public void randomStringArray(String[] array) {
        char[] chars = new char[5];
        for (int i = 0; i < array.length; i++) {
            randomCharArray(chars);
            array[i] = new String(chars);
        }
    }

    // 对数组进行排序，按照每个字符串的首字母排序（无视大小写）
    public void sort(String[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                char left = array[j].charAt(0);
                char right = array[j + 1].charAt(0);
                if (left >= 97) { // 忽视大小写
                    left -= 32;
                }
                if (right >= 97) {
                    right -= 32;
                }
                if (left > right) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        StringOperator operator = new StringOperator();
        char[] randChars = new char[5];
        operator.randomCharArray(randChars);
        System.out.println(Arrays.toString(randChars));

        String[] randStrings = new String[8];
        operator.randomStringArray(randStrings);
        System.out.println(Arrays.toString(randStrings));

        String[] tempRandStrings = new String[randStrings.length];
        System.arraycopy(randStrings, 0, tempRandStrings, 0, randStrings.length);

        System.out.println("Sort by Arrays: ");
        Arrays.sort(randStrings);
        System.out.println(Arrays.toString(randStrings));

        System.out.println("Sort by StringOperator: ");
        operator.sort(tempRandStrings);
        System.out.println(Arrays.toString(tempRandStrings));
    }

}