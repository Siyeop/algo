package boj.boj1005_ACMCraft;

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

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            // 1. 문제 읽기
            // 1-1. 기초 정보
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 건물 수
            int K = Integer.parseInt(st.nextToken()); // 건설 순서

            // 1-2. 건물별 시간
            Graph graph = new Graph(N);
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) graph.setCost(i, Integer.parseInt(st.nextToken()));

            // 1-3. 건설순서
            for(int i=1; i<=K; i++) {
                st = new StringTokenizer(br.readLine());
                graph.addEdge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // 2. 풀이 - 위상정렬
            graph.dag();

            // 3. 출력
            int answer = graph.getAnswer(Integer.parseInt(br.readLine()));
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    int size;
    Node[] nodes;

    public Graph(int size) {
        this.size = size;
        this.nodes = new Node[size+1];
        for(int i=0; i<=size; i++) nodes[i] = new Node();
    }

    public void setCost(int no, int cost) {
        nodes[no].cost = cost;
        nodes[no].cumCost = cost;
    }

    public int getAnswer(int no) {
        return nodes[no].cumCost;
    }

    public void addEdge(int from, int to) {
        nodes[from].outList.add(to);
        nodes[to].inList.add(from);
        nodes[to].inCount++;
    }

    public void dag() {
        Queue<Node> queue = new LinkedList<>();


        // 1. indegree 0인 노드를 Queue에 저장
        for(int i=1; i<=size; i++) if(nodes[i].inCount==0) queue.add(nodes[i]);

        // 2. Queue가 비어질 때까지 꺼냄
        while(!queue.isEmpty()) {
            Node polled = queue.poll();

            // 3. Max(들어오는 노드까지 걸린시간 + 내 건설시간)
            for(int in : polled.inList) {
                polled.cumCost = Math.max(polled.cumCost, polled.cost + nodes[in].cumCost);
            }

            // 4. 나가는 노드의 indegree 줄여주고, indegree 0이면 queue에 추가
            for(int out : polled.outList) {
                nodes[out].inCount--;
                if(nodes[out].inCount == 0) queue.add(nodes[out]);
            }
        }
    }
}

class Node {
    List<Integer> inList = new ArrayList<>();
    int inCount = 0;
    List<Integer> outList = new ArrayList<>();
    int cost = 0;
    int cumCost = 0;
}
