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
        // 1-1. 기본 정보, 0.5초
        int N = Integer.parseInt(br.readLine().trim()); // 1 ~ 1,000
        int M = Integer.parseInt(br.readLine().trim()); // 1 ~ 100,000

        Graph graph = new Graph(N);

        // 1-2. 버스 정보
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 1-3. 출발 도착점 정보
        st = new StringTokenizer(br.readLine().trim());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 2. 계산
        graph.dijkstra(start);

        bw.write(graph.cost[end] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {

    int size;
    List<Edge>[] edgeList;
    int[] cost;
    boolean[] visited;

    public Graph(int size) {
        this.size = size;
        this.edgeList = new ArrayList[size+1];
        for(int i=0; i<=size; i++) {
            this.edgeList[i] = new ArrayList<>();
        }
        this.cost = new int[size+1];
        this.visited = new boolean[size+1];
    }

    public void addEdge(int from, int to, int cost) {
        this.edgeList[from].add(new Edge(to, cost));
    }

    public void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);

        Arrays.fill(this.cost, Integer.MAX_VALUE);
        this.cost[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node polled = pq.poll();
            int curr = polled.no;

            if (!this.visited[curr]) {
                this.visited[curr] = true;

                for (Edge edge : this.edgeList[curr]) {
                    int nCost = cost[curr] + edge.cost;
                    if (!this.visited[edge.to] && cost[edge.to] > nCost) {
                        cost[edge.to] = nCost;
                        pq.add(new Node(edge.to, cost[edge.to]));
                    }
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

class Node {
    int no;
    int cost;

    public Node(int no, int cost) {
        this.no = no;
        this.cost = cost;
    }
}