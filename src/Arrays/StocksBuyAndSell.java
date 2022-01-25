package Arrays;
import java.io.*;
import java.util.*;
public class StocksBuyAndSell {
    public static void bestWithStartingAndEnding() {
        //        time - O(n), space - O(1)
        int[] arr = {5,2,6,1,4};
        int maxProfit = 0;
        int minBuyingDay = 0;
        int maxBuyingDay = 0;
        int maxSellingDay = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < arr[minBuyingDay]) {
                minBuyingDay = i;
            }
            int currentProfit = arr[i] - arr[minBuyingDay];
            if(currentProfit > maxProfit) {
                maxProfit = currentProfit;
                maxSellingDay = i;
                maxBuyingDay = minBuyingDay;
            }
        }
        System.out.println("Buying Day - " + maxBuyingDay + " Selling Day - " + maxSellingDay);
    }
    public static void best() {
        //        time - O(n), space - O(1)
        int[] prices = {5,2,6,1,4};
        int minSoFar = prices[0];
        int maxProfit = 0;
        for(int i = 0; i < prices.length; i++) {
            minSoFar = Math.min(prices[i], minSoFar);
            maxProfit = Math.max(maxProfit, prices[i] - minSoFar);
        }
        System.out.println(maxProfit);
    }
    public static void better() {
//        time - O(n), space - O(n)
        int[] arr = {5,2,6,1,4};
        int[] max = new int[arr.length];
        max[arr.length - 1] = arr[arr.length - 1];
        for(int i = arr.length - 2; i >= 0; i--) {
            max[i] = Math.max(max[i+1], arr[i]);
        }
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            maxProfit = Math.max(maxProfit, max[i] - arr[i]);
        }
        System.out.println(maxProfit);
    }
    public static void brute() {
        //        time - O(n^2), space - O(1)
        int maxProfit = Integer.MIN_VALUE;
        int[] arr = {5,2,6,1,4};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                maxProfit = Math.max(maxProfit, arr[j] - arr[i]);
            }
        }
        System.out.println(maxProfit);
    }
}
