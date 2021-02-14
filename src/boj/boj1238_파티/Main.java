package boj.boj1238_파티;

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

        // 1. 기초정보
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int M = Integer.parseInt(st.nextToken()); // 도로 수
        int X = Integer.parseInt(st.nextToken()); // 모이는 마을 번호
        Graph g1 = new Graph(N); // 정방향
        Graph g2 = new Graph(N); // 역방향

        // 2. 간선정보
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            g1.addEdge(from, to, cost);
            g2.addEdge(to, from, cost);
        }

        // 3. 다익스트라
        g1.dijkstra(X);
        g2.dijkstra(X);

        // 4. 오래걸리는 학생 소요시간 계산
        int answer = 0;
        for(int i=1; i<=N; i++) answer = Math.max(answer, g1.cost[i] + g2.cost[i]);
        bw.write(answer + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    static final int INF = Integer.MAX_VALUE;

    int size;
    List<Edge>[] edgeList;
    int[] cost;

    public Graph(int size) {
        this.size = size;
        this.edgeList = new ArrayList[size+1];
        for(int i=1; i<=size; i++) edgeList[i] = new ArrayList<>();

        cost = new int[size+1];
        Arrays.fill(cost, INF);
    }

    public void addEdge(int from, int to, int cost) {
        this.edgeList[from].add(new Edge(to, cost));
    }

    public void dijkstra(int start) {
        // 1. 초기화
        this.cost[start] = 0;
        Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        pq.add(new Node(start, 0));

        // 2. 최단거리 계산
        while(!pq.isEmpty()) {
            Node polled = pq.poll();
            for(Edge edge : edgeList[polled.num]) {
                if(polled.cost + edge.cost < this.cost[edge.to]) {
                    this.cost[edge.to] = polled.cost + edge.cost;
                    pq.add(new Node(edge.to, this.cost[edge.to]));
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
    int num;
    int cost;

    public Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}