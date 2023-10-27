package com.yooha.luckyboy;

public class LuckyUtil {

    static {
        System.loadLibrary("lucky");
    }

    public static native int get_lucky_number(int[] balls);


}
