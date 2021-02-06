package boj.boj1197_최소스패닝트리;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int[] group;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        // 1-1. 기초 정보
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 노드 수
        int E = Integer.parseInt(st.nextToken()); // 간선 수

        // 1-2. 간선 정보, 오름차순 정렬
        List<Edge> edgeList = new ArrayList<>();
        for(int i=1; i<=E; i++) {
            st = new StringTokenizer(br.readLine());
            edgeList.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(edgeList);

        // 2. Kruskal
        // 2-1. 초기화
        group = new int[V+1];
        for(int i=1; i<=V; i++) group[i] = i;

        long result = 0L;
        int unionCount = 0;

        // 2-2. 에지 돌면서 그룹 다른것들 더함
        for(Edge edge : edgeList) {
            if(find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                result += edge.cost;
                if(++unionCount == V-1) break;
            }
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static int find(int num) {
        if(group[num] != num) group[num] = find(group[num]);
        return group[num];
    }

    static void union(int num1, int num2) {
        if(group[find(num1)] < group[find(num2)]) group[find(num2)] = group[find(num1)];
        else group[find(num1)] = group[find(num2)];
    }

}

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge e) {
        return Integer.compare(this.cost, e.cost);
    }
}