package boj.boj2178_미로탐색;

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

        st = new StringTokenizer(br.readLine().trim());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N, M);
        for (int i = 1; i <= N; i++) {
            graph.setRow(i, br.readLine().trim());
        }

        bw.write(graph.bfs() + "\n");


        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    int r;
    int c;
    char[][] map;
    int[][] visited;

    public Graph(int r, int c) {
        this.r = r;
        this.c = c;

        map = new char[r + 2][c + 2];
        visited = new int[r + 2][c + 2];

        for(int i=1; i<=r; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
    }

    public void setRow(int no, String row) {
        for (int i = 1; i <= c; i++) {
            map[no][i] = row.charAt(i - 1);
        }
    }

    public int bfs() {
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        queue.add(Pair.of(1, 1));
        visited[1][1] = 1;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> polled = queue.poll();

            for (int i = 0; i < 4; i++) {
                int row = polled.row + dirs[i][0];
                int col = polled.col + dirs[i][1];
                if (map[row][col] == '1' && visited[polled.row][polled.col] + 1 < visited[row][col]) {
                    visited[row][col] = visited[polled.row][polled.col] + 1;
                    queue.add(Pair.of(row, col));
                    if(row == this.r && col == this.c) {
                        return visited[row][col];
                    }
                }
            }
        }

        return -1;
    }
}


class Pair<T, S> {
    T row;
    S col;

    public Pair(T row, S col) {
        this.row = row;
        this.col = col;
    }

    static <T, S> Pair<T, S> of(T row, S col) {
        return new Pair<T, S>(row, col);
    }
}