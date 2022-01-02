package boj.boj11779_최소비용구하기2;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        // 1-1. N, M
        int N = Integer.parseInt(br.readLine().trim()); // 1 ~ 1,000
        int M = Integer.parseInt(br.readLine().trim()); // 1 ~ 100,000

        Graph graph = new Graph(N);

        // 1-2. 연결정보
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 1-3. 출발, 도착점 정보
        st = new StringTokenizer(br.readLine().trim());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 2. 계산
        graph.dijkstra(start);
        graph.calcShortestRoute(start, end);

        // 3. 출력
        bw.write(graph.cost[end] + "\n");
        bw.write(graph.route.size() + "\n");
        bw.write(graph.route.stream().map(String::valueOf).collect(Collectors.joining(" ")) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    int size;
    int[] cost;
    int[] prev;
    List<Node>[] edgeList;
    boolean[] visited;
    List<Integer> route;

    public Graph(int size) {
        this.size = size;
        this.cost = new int[size+1];
        this.prev = new int[size+1];
        this.edgeList = new ArrayList[size+1];
        for(int i=0; i<=size; i++) this.edgeList[i] = new ArrayList<>();
        this.visited = new boolean[size+1];
        this.route = new ArrayList<>();
    }

    public void addEdge(int from, int to, int cost) {
        this.edgeList[from].add(new Node(to, cost));
    }

    public void dijkstra(int start) {

        Arrays.fill(visited, false);
        Arrays.fill(cost, Integer.MAX_VALUE);
        this.cost[start] = 0;

        Queue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.cost - n2.cost);
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node polled = pq.poll();

            if(!this.visited[polled.to]) {
                this.visited[polled.to] = true;
                for(Node edge : this.edgeList[polled.to]) {
                    int nCost = polled.cost + edge.cost;
                    if(this.cost[edge.to] > nCost && !this.visited[edge.to]) {
                        this.cost[edge.to] = nCost;
                        pq.add(new Node(edge.to, nCost));
                        this.prev[edge.to] = polled.to;
                    }
                }
            }
        }
    }

    public void calcShortestRoute(int start, int end) {

        int prev = end;
        this.route.add(prev);
        while(prev != start) {
            prev = this.prev[prev];
            this.route.add(prev);
        }

        Collections.reverse(this.route);
    }
}

class Node {
    int to;
    int cost;

    public Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}
