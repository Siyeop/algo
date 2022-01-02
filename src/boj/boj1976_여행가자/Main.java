package boj.boj1976_여행가자;

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
        // 1-1. 기본 정보
        int N = Integer.parseInt(br.readLine().trim()); // ~ 200
        int M = Integer.parseInt(br.readLine().trim()); // ~ 1,000

        Graph graph = new Graph(N);

        // 1-2. 연결 정보
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int j=1; j<=N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) {
                    graph.union(i, j);
                }
            }
        }

        // 2. 풀이
        st = new StringTokenizer(br.readLine().trim());

        Set<Integer> groups = new HashSet<>();
        for(int i=1; i<=M; i++) {
            groups.add(graph.find(Integer.parseInt(st.nextToken())));
        }

        // 3. 출력
        if(groups.size() == 1) {
            bw.write("YES\n");
        } else {
            bw.write("NO\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {

    int size;
    int[] group;

    public Graph(int size) {
        this.size = size;
        this.group = new int[size+1];
        for(int i=1; i<=size; i++) {
            this.group[i] = i;
        }
    }

    public void union(int n1, int n2) {
        if(find(n1) != find(n2)) {
            if(find(n1) <= find(n2)) group[find(n2)] = find(n1);
            else group[find(n1)] = find(n2);
        }
    }

    public int find(int n) {
        if(this.group[n] != n) {
            this.group[n] = find(group[n]);
        }
        return group[n];
    }
}

