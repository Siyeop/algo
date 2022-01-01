package boj.boj1963_소수경로;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine());

        Graph graph = new Graph();

        for(int tc=1; tc<=TC; tc++) {
            st = new StringTokenizer(br.readLine().trim());

            int result = graph.bfs(st.nextToken(), st.nextToken());

            if(result == -1) {
                bw.write("Impossible\n");
            } else {
                bw.write(result + "\n");
            }
        }



        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {

    boolean[] prime = new boolean[10001];
    int[] visited = new int[10001];

    public Graph() {
        Arrays.fill(prime, true);

        for(int i=2; i<10000; i++) {
            if(prime[i]) {
                for(int j=i+i; j<10000; j+=i) {
                    prime[j] = false;
                }
            }
        }
    }

    public int bfs(String start, String end) {
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[Integer.parseInt(start)] = 0;

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            String polled = queue.poll();
            if(polled.equals(end)) {
                return visited[Integer.parseInt(polled)];
            }

            for(int i=0; i<4; i++) {
                for(int j=(i==0) ? 1 : 0; j<10; j++) {

                    int target = Integer.parseInt(polled.substring(0, i) + j + polled.substring(i+1, 4));

                    int candidate = visited[Integer.parseInt(polled)] + 1;
                    if(this.prime[target] && candidate < visited[target]) {
                        visited[target] = candidate;
                        queue.add(Integer.toString(target));
                    }
                }
            }
        }
        return -1;
    }
}