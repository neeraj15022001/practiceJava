package Graphs;

import java.io.*;
import java.util.*;

//Greedy Algorithms
public class Dijkstra {
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

    private static void dijsktraLogic(int[][] edges, int vertices) {
        boolean[] visited = new boolean[vertices];
        int[] distances = new int[vertices];
        int[] parents = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        Arrays.fill(visited, false);
        distances[0] = 0;
        int handled = 0;
        while (handled != vertices) {
            int minVertex = pickMin(distances, visited); //Use PriorityQueue to reduce time
            visited[minVertex] = true;
            for (int i = 0; i < vertices; i++) { // Use Adjacency List instead of Matrix to reduce time
                if (edges[minVertex][i] != 0 && !visited[i]) {
                    if (edges[minVertex][i] + distances[minVertex] < distances[i]) {
                        distances[i] = edges[minVertex][i] + distances[minVertex];
                        parents[i] = minVertex;
                    }
                }
            }
            handled++;
        }
        for (int i = 0; i < distances.length; i++) {
            System.out.print(i + " " + distances[i]);
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
        dijsktraLogic(edgesArr, vertices);
    }

    public static void dijkstra() {
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
