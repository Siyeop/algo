package boj.boj1916_최소비용구하기;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        // 1-1. 기초 정보
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 1-2. 간선 정보
        Graph graph = new Graph(N);
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 2. 풀이
        // 2-1. 다익스트라
        st = new StringTokenizer(br.readLine());
        graph.setPoint(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        graph.dijkstra();
        bw.write(graph.getAnswer() + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}


class Graph {
    int size;
    List<Edge>[] edgeList;
    int start;
    int end;
    long[] cost;

    public Graph(int size) {
        this.size = size;
        edgeList = new ArrayList[size+1];
        for(int i=0; i<=size; i++) edgeList[i] = new ArrayList<>();
        cost = new long[size+1];
        Arrays.fill(cost, Long.MAX_VALUE);
    }

    public void addEdge(int from, int to, int cost) {
        edgeList[from].add(new Edge(to, cost));
    }

    public void setPoint(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void dijkstra() {
        cost[start] = 0L;

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0L));

        while(!pq.isEmpty()) {
            Node polled = pq.poll();
            for(Edge edge : edgeList[polled.num]) {
                if(cost[polled.num] + edge.cost < cost[edge.to]) {
                    cost[edge.to] = cost[polled.num] + edge.cost;
                    pq.add(new Node(edge.to, cost[edge.to]));
                }
            }
        }
    }

    public long getAnswer() {
        return cost[end];
    }
}

class Node implements Comparable<Node> {
    int num;
    long cost;

    public Node(int num, long cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}

class Edge {
    int to;
    long cost;

    public Edge(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }
}
