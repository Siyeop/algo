package boj.boj2252줄세우기;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

// https://www.acmicpc.net/problem/2252

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N; // 사람 수
    static int M; // 쿼리 수

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽고 그래프 초기화
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N);

        int from, to;
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            graph.nodeMap.get(from).addNextNode(to);
        }

        // 2. DAG 구하기
        graph.dag();

        // 3. 출력
        for(int i=0; i<graph.dag.size()-1; i++) {
            bw.write(graph.dag.get(i) + " ");
        }
        bw.write(graph.dag.get(graph.dag.size()-1).toString());

        bw.write("\n");


        // 4. 정리
        bw.flush();
        bw.close();
        br.close();
    }
}


class Graph {
    int nodeCount;
    Map<Integer, Node> nodeMap;
    List<Integer> dag = new ArrayList<>();

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        this.nodeMap = new HashMap<>();

        for (int nodeKey = 1; nodeKey <= nodeCount; nodeKey++) {
            nodeMap.put(nodeKey, new Node(nodeKey));
        }
    }

    public void dag() {
        for(Integer key : nodeMap.keySet()) {
            dfs(key);
        }

        Collections.reverse(dag);
    }

    public void dfs(int key) {
        Node node = nodeMap.get(key);
        if(node.visited) {
            return;
        }
        for(Integer nodeKey : node.nextNodeKeyList) {
            dfs(nodeKey);
        }

        dag.add(node.key);
        node.visited = true;
    }
}

class Node {
    int key;
    boolean visited;
    List<Integer> nextNodeKeyList;

    public Node(int key) {
        this.key = key;
        this.visited = false;
        nextNodeKeyList = new ArrayList<>();
    }

    public void addNextNode(int nodeKey) {
        this.nextNodeKeyList.add(nodeKey);
    }
}