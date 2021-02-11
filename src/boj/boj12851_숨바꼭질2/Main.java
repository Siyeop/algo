package boj.boj12851_숨바꼭질2;

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
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 시작 (0 ≤ N ≤ 100,000)
        int K = Integer.parseInt(st.nextToken()); // 도착 (0 ≤ K ≤ 100,000)

        // 2. BFS
        // 2-1. 선언
        int MIN_IDX = 0;
        int MAX_IDX = Math.min(Math.max((N+1)*2, (K+1)*2), 100000);
        int UNVISITED = -1;
        int[] visited = new int[MAX_IDX+1];
        int[] way = new int[MAX_IDX+1];

        // 2-2. 초기화
        Arrays.fill(visited, -1);
        Arrays.fill(way, 0);
        visited[N] = 0;
        way[N] = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(N);

        // 2-3. 수행
        while(!queue.isEmpty()) {
            int polled = queue.poll();
            if(visited[K] != UNVISITED && visited[polled] >= visited[K]) break;

            if(polled-1 >= MIN_IDX) {
                if(visited[polled-1] == UNVISITED) {
                    queue.add(polled-1);
                    visited[polled-1] = visited[polled] + 1;
                }
                if(visited[polled-1] == visited[polled] + 1) {
                    way[polled-1] += way[polled];
                }
            }
            if(polled+1 <= MAX_IDX) {
                if(visited[polled+1] == UNVISITED) {
                    queue.add(polled+1);
                    visited[polled+1] = visited[polled] + 1;
                }
                if(visited[polled+1] == visited[polled] + 1) {
                    way[polled+1] += way[polled];
                }
            }
            if(polled*2 <= MAX_IDX) {
                if(visited[polled*2] == UNVISITED) {
                    queue.add(polled*2);
                    visited[polled*2] = visited[polled] + 1;
                }
                if(visited[polled*2] == visited[polled] + 1) {
                    way[polled*2] += way[polled];
                }
            }
        }

        bw.write(visited[K] + "\n");
        bw.write(way[K] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

