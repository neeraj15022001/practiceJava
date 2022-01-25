package Strings;

import java.io.*;
import java.util.*;

public class RomanToInteger {
    public static void brute() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        String str = "XV";
        int index = 0;
        int ans = 0;
        int length = str.length();
        while (index < length) {
            if (index == length - 1) {
                ans += map.get(str.charAt(index));
                break;
            }
            int a = map.get(str.charAt(index));
            int b = map.get(str.charAt(index + 1));
            if (a < b) {
                ans += -a;
            } else {
                ans += a;
            }
            index++;
        }
        System.out.println(ans);
    }
}
