package Graphs;

import java.util.*;

public class Graph {
    public static void getConnectedComponentsLogic(int[][] edges, int sv, boolean[] visited, ArrayList<Integer> temp) {
        int n = edges.length;
        visited[sv] = true;
        temp.add(sv);
        for (int i = 0; i < n; i++) {
            if (edges[sv][i] == 1 && !visited[i]) {
                getConnectedComponentsLogic(edges, i, visited, temp);
            }
        }
    }

    public static void getConnectedComponentsHelper(int[][] edges) {
        int n = edges.length;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ArrayList<Integer> temp = new ArrayList<>();
                getConnectedComponentsLogic(edges, i, visited, temp);
                Collections.sort(temp);
                for (int element : temp) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        }
    }

    public static void getConnectedComponents() {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int[][] edges = new int[v][v];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            edges[fv][sv] = 1;
            edges[sv][fv] = 1;
        }
        getConnectedComponentsHelper(edges);
    }

    public static void isConnected(int[][] edges, int sv, boolean[] visited) {
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            if (edges[sv][i] == 1 && !visited[i]) {
                visited[i] = true;
                isConnected(edges, i, visited);
            }
        }
    }

    public static boolean isConnectedHelper(int[][] edges) {
        int n = edges.length;
        boolean[] visited = new boolean[n];
        isConnected(edges, 0, visited);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean getIsConnected() {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        if (v <= 1)
            return true;
        int[][] edges = new int[v][v];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            edges[fv][sv] = 1;
            edges[sv][fv] = 1;
        }
        return isConnectedHelper(edges);
    }

    public static void getPath(int[][] edges, int sv, boolean[] visited, int findsv) {
        int n = edges.length;
        Queue<Integer> order = new LinkedList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        order.add(sv);
        visited[sv] = true;
        while (!order.isEmpty()) {
            int removedElement = order.remove();
            if (removedElement == findsv) {
                int adjacent = map.get(findsv);
                System.out.print(findsv + " ");
                while (adjacent != sv) {
                    System.out.print(adjacent + " ");
                    adjacent = map.get(adjacent);
                }
                System.out.print(adjacent + " ");
            } else {
                for (int i = 0; i < n; i++) {
                    if (edges[removedElement][i] == 1 && !visited[i]) {
                        map.put(i, removedElement);
                        order.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
    }

    public static void getPathHelper(int[][] edges, int findfv, int findsv) {
        int n = edges.length;
        boolean[] visited = new boolean[n];
        getPath(edges, findfv, visited, findsv);
    }

    public static void getFullPath() {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int[][] edges = new int[v][v];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            edges[fv][sv] = 1;
            edges[sv][fv] = 1;
        }
        int findfv = sc.nextInt();
        int findsv = sc.nextInt();
        getPathHelper(edges, findfv, findsv);
    }

    public static boolean hasPath(int[][] edges, int sv, boolean[] visited, int findsv) {
        int n = edges.length;
        Queue<Integer> order = new LinkedList<Integer>();
        order.add(sv);
        visited[sv] = true;
        while (!order.isEmpty()) {
            int removedElement = order.remove();
            if (removedElement == findsv) {
                return true;
            } else {
                for (int i = 0; i < n; i++) {
                    if (edges[removedElement][i] == 1 && !visited[i]) {
                        order.add(i);
                        visited[i] = true;
                    }
                }
            }
        }
        return false;
//        for (int i = 0; i < n; i++) {
//            if (edges[sv][i] == 1 && !visited[i]) {
//                visited[i] = true;
//                if (i == findsv)
//                    return true;
//                return hasPath(edges, i, visited, findsv);
//            }
//        }
//        return false;
    }

    public static boolean hasPathHelper(int[][] edges, int findfv, int findsv) {
        int n = edges.length;
        boolean[] visited = new boolean[n];
        return hasPath(edges, findfv, visited, findsv);
    }

    public static boolean findHasPath() {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();
        int[][] edges = new int[v][v];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            edges[fv][sv] = 1;
            edges[sv][fv] = 1;
        }
        int findfv = sc.nextInt();
        int findsv = sc.nextInt();
        return hasPathHelper(edges, findfv, findsv);
    }

    public static void printHelper(int[][] edges, int sv, boolean[] visited) {
        System.out.println(sv);
        visited[sv] = true;
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            if (edges[sv][i] == 1 && !visited[i]) {
                printHelper(edges, i, visited);
            }
        }
    }

    public static void print(int[][] edges) {
        boolean[] visited = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            if (!visited[i]) {
                printHelper(edges, i, visited);
            }
        }
    }

    public static void printGraph() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
//        min edges(acyclic) = n - 1, max(cyclic) = (n(n-1))/2
        int[][] edges = new int[n][n];
        for (int i = 0; i < e; i++) {
            int fv = sc.nextInt();
            int sv = sc.nextInt();
            edges[fv][sv] = 1;
            edges[sv][fv] = 1;
        }
        print(edges);
    }
}
