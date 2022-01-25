package Strings;

import java.io.*;
import java.util.*;

public class LongestPalindrome {
    private static void printStr(String s, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }

    public static int brute() {
//        Time - O(n^3) Space - O(1)
        int maxLength = 1;
        String str = "forgeeksskeegfor";
        int start = 0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int flag = 1;
                for (int k = 0; k < (j - i + 1) / 2; k++) {
                    if (str.charAt(i + k) != str.charAt(j - k)) {
                        flag = 0;
                        break;
                    }
                }
                if (flag != 0 && (j - i + 1) > maxLength) {
                    start = i;
                    maxLength = j - i + 1;
                }
            }
        }
        System.out.println("Longest Palindrome");
//        System.out.println(str + " start - " + start + " ending - " + (start + maxLength - 1));
        printStr(str, start, start + maxLength - 1);
        return maxLength;
    }

    public static int dp() {
        String str = "forgeeksskeegfor";
        int start = 0, maxLength = 1;
        int count = 0;
        int length = str.length();
        int[][] solution = new int[length][length];
//        substring of length 1
        for (int[] i : solution) {
            Arrays.fill(i, 0);
        }
        for (int i = 0; i < length; i++) {
            solution[i][i] = 1;
            count++;
        }
//        Substring of length 2
        for (int i = 0; i < length - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                solution[i][i + 1] = 1;
                count++;
                start = i;
                maxLength = 2;
            }
        }
        for (int k = 2; k < length; k++) {
            for (int i = 0, j = k; j < length; j++, i++) {
                if (str.charAt(i) == str.charAt(j) && solution[i + 1][j - 1] == 1) {
                    solution[i][j] = 1;
                    count++;
                    if ((k + i) > maxLength)  {
                        start = i;
                        maxLength = k + 1;
                    }
                }
            }
        }
        System.out.println(count);
        printStr(str, start, start + maxLength - 1);
        return maxLength;
    }
}
