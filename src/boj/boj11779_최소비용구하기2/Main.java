package boj.boj11779_최소비용구하기2;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int M;
    static int S;
    static int E;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        // 1-1. 기본 정보
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        Graph graph = new Graph(N);

        // 1-2. 간선 정보
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 1-3. 출발, 도착점
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 2. 풀이
        graph.dijkstra(S);
        graph.setAnswer(S, E);

        // 3. 출력
        bw.write(graph.cost[E] + "\n");
        bw.write( graph.pathNodes + "\n");
        bw.write( graph.path + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    int size;
    List<Edge>[] adjList;
    int[] cost;
    int[] prev;
    int pathNodes;
    String path;

    public Graph(int size) {
        this.size = size;
        adjList = new ArrayList[size+1];
        for(int i=1; i<=size; i++) adjList[i] = new ArrayList<>();
        this.cost = new int[size+1];
        this.prev = new int[size+1];
    }

    public void addEdge(int from, int to, int cost) {
        adjList[from].add(new Edge(to, cost));
    }

    public void dijkstra(int start) {
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        cost[start] = 0;

        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node polled = pq.poll();
            for(Edge edge : adjList[polled.num]) {
                if(polled.cost + edge.cost < cost[edge.to]) {
                    cost[edge.to] = polled.cost + edge.cost;
                    prev[edge.to] = polled.num;
                    pq.add(new Node(edge.to, cost[edge.to]));
                }
            }
        }
    }

    public void setAnswer(int start, int end) {
        Deque<Integer> deque = new ArrayDeque<>();
        int curr = end;
        while(prev[curr] != -1) {
            deque.addFirst(curr);
            curr = prev[curr];
        }
        deque.addFirst(start);
        this.pathNodes = deque.size();

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) sb.append(deque.pollFirst()).append(" ");
        this.path = sb.toString().trim();
    }
}

class Node implements Comparable<Node> {
    int num;
    int cost;

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
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