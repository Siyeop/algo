package boj.boj1854_K번째최단경로찾기;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M, K;  // N 도시 수, M 도로 수, N번째 최단경로

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        // 1-1. 기초 정보
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Graph graph = new Graph(N, K);

        // 1-2. 간선 정보
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 2. 풀이
        // 2-1. 다익스트라
        graph.dijkstra();

        // 3. 출력
        for(int i=1; i<=N; i++) {
            bw.write((graph.costList[i].peek() != Integer.MAX_VALUE ? graph.costList[i].peek() : -1)  + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    int size;
    int k;
    List<Edge>[] edgeList;
    Queue<Integer>[] costList;

    @SuppressWarnings("unchecked")
    public Graph(int size, int k) {
        this.size = size;
        this.k = k;
        edgeList = new ArrayList[size+1];
        costList = new PriorityQueue[size+1];
        for(int i=1; i<=size; i++) {
            edgeList[i] = new ArrayList<>();
            costList[i] = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for(int j=1; j<=k; j++) costList[i].add(Integer.MAX_VALUE);
        }
    }

    public void addEdge(int from, int to, int cost) {
        edgeList[from].add(new Edge(to, cost));
    }

    public void dijkstra() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        costList[1].poll();
        costList[1].add(0);

        while(!pq.isEmpty()) {
            Node polled = pq.poll();

            for(Edge edge : edgeList[polled.num]) {
                if(polled.cost + edge.cost < costList[edge.to].peek()) {
                    costList[edge.to].add(polled.cost + edge.cost);
                    costList[edge.to].poll();
                    pq.add(new Node(edge.to, polled.cost + edge.cost));
                }
            }
        }
    }
}

class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class Node implements Comparable<Node> {
    int num;
    int cost;

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}