package me.zhang.tdd;

/**
 * Created by zhangxiangdong on 2017/4/14.
 */
public class Fibonacci {

    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

}
