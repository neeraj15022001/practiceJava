package Graphs;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

//Greedy Algorithm
public class Kruskal {
    public static void kruskal() {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        Edge[] inputArr = new Edge[edges];
        for (int i = 0; i < inputArr.length; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int weight = sc.nextInt();
            Edge o = new Edge(source, destination, weight);
            inputArr[i] = o;
        }
        Arrays.sort(inputArr);
        int count = 0;
        int[] parents = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parents[i] = i;
        }
        Edge[] output = new Edge[vertices - 1];
        int itr = 0;
        while (count != (vertices - 1) && itr < inputArr.length) {
            Edge e = inputArr[itr];
            int v1 = e.source;
            int v2 = e.destination;
            int p1 = parents[v1], p2 = parents[v2];
            int vertex1 = v1, vertex2 = v2;
            while (vertex1 != p1) {
                vertex1 = p1;
                p1 = parents[p1];
            }
            while (vertex2 != p2) {
                vertex2 = p2;
                p2 = parents[p2];
            }
            if (p1 != p2) {
                parents[p2] = p1;
                output[count] = inputArr[count];
                count++;
            }
            itr++;
        }
        for (Edge e : output) {
            int v1 = e.source;
            int v2 = e.destination;
            System.out.print(v1 < v2 ? v1 + " " + v2 + " " : v2 + " " + v1 + " ");
            System.out.print(e.weight);
            System.out.println();
        }
    }
}
