package boj.boj1260DFS와BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken()); // 노드 수
        int M = Integer.parseInt(st.nextToken()); // 에지 수
        int V = Integer.parseInt(st.nextToken()); // 시작 노드 번호

        Graph graph = new Graph(N);


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());;
            graph.addEdge(a, b);
        }

        graph.sortEdge();

        graph.dfs(V);
        graph.reset();
        graph.bfs(V);

    }

}

class Graph {
    List<Integer>[] edgeList;
    boolean[] visited;

    public Graph(int size) {
        visited = new boolean[size + 1];
        edgeList = new ArrayList[size+1];
        for(int i=0; i<=size; i++) {
            edgeList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int s, int e) {

        edgeList[s].add(e);
        edgeList[e].add(s);
    }

    public void sortEdge() {
        for(List<Integer> edges : edgeList) {
            Collections.sort(edges);
        }
    }

    public void reset() {
        Arrays.fill(visited, false);
        System.out.println("");
    }

    void dfs(int s) {
        if (visited[s]) return;

        visited[s] = true;
        System.out.print(s + " ");

        for(int e : edgeList[s]) {
            if(!visited[e]) {
                dfs(e);
            }
        }
    }

    void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");
            for (int e : edgeList[s]) {
                if (!visited[e]) {
                    queue.add(e);
                    visited[e] = true;
                }
            }
        }
    }
}