package Graphs;

import java.io.*;
import java.util.*;

//Greedy Algorithms
public class Prim {
    private static int pickMin(int[] weights, boolean[] visited) {
        int itr = 0;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        while (itr != weights.length) {
            if (!visited[itr] && weights[itr] < min) {
                min = weights[itr];
                minIndex = itr;
            }
            itr++;
        }
        return minIndex;
    }

    private static void primLogic(int[][] edges, int vertices) {
        boolean[] visited = new boolean[vertices];
        int[] weights = new int[vertices];
        int[] parents = new int[vertices];
        for (int i = 1; i < vertices; i++) {
            weights[i] = Integer.MAX_VALUE;
        }
        Arrays.fill(parents, -1);
        Arrays.fill(visited, false);

        int handled = 0;
        while (handled != vertices) {
            int minVertex = pickMin(weights, visited); //Use PriorityQueue to reduce time
            visited[minVertex] = true;
            for (int i = 0; i < vertices; i++) { // Use Adjacency List instead of Matrix to reduce time
                if (edges[minVertex][i] != 0 && !visited[i]) {
                    if (edges[minVertex][i] < weights[i]) {
                        weights[i] = edges[minVertex][i];
                        parents[i] = minVertex;
                    }
                }
            }
            handled++;
        }
        for (int i = 0; i < parents.length; i++) {
            System.out.print(i + " - " + parents[i]);
            System.out.println();
        }
    }
//    Current Time Complexity - O(v^2)
//    Can be Reduced to - O((E+V)logE)
    private static void takeInput() {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        int[][] edgesArr = new int[vertices][vertices];
        for (int i = 0; i < edges; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            int weight = sc.nextInt();
            edgesArr[fv][sv] = weight;
            edgesArr[sv][fv] = weight;
        }
        primLogic(edgesArr, vertices);
    }

    public static void prim() {
        takeInput();
    }

//    public static int fact(int n) {
//        if(n == 0 || n == 1) {
//            return 1;
//        } else {
//            return n * fact(n-1);
//        }
//    }
//    public static int fibb(int n) {
//        if(n == 0 || n == 1) {
//            return n;
//        }
//        else {
//            int a = fibb(n-1);
//            int b = fibb(n-2);
//            return a + b;
//        }
//    }
}
