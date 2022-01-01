package boj.boj2573_빙산;

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

        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N, M);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < M; j++) {
                graph.setMap(i, j, Integer.parseInt(st.nextToken()));
            }
        }

        graph.calc();

        bw.write(graph.time + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {

    int row, col;
    int[][] map;
    int[][] meltMap;

    boolean[][] visited;
    int visitedCount = 0;

    int iceCount = 0;
    int time = 0;
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    int sRow;
    int sCol;

    public Graph(int row, int col) {
        this.row = row;
        this.col = col;
        this.map = new int[row][col];
        this.meltMap = new int[row][col];
        this.visited = new boolean[row][col];
    }

    public void setMap(int row, int col, int height) {
        this.map[row][col] = height;
        if (height > 0) {
            this.iceCount++;
            this.sRow = row;
            this.sCol = col;
        }
    }

    public void calc() {

        while (true) {
            if (iceCount == 0) {
                this.time = 0;
                break;
            }

            this.visitedCount = 0;
            for (int i = 1; i < row-1; i++) Arrays.fill(visited[i], false);

            this.dfs(this.sRow, this.sCol);

            if (visitedCount != iceCount) {
                break;
            }

            melt();
        }
    }

    public void dfs(int row, int col) {

        visited[row][col] = true;
        visitedCount++;

        for (int[] dir : dirs) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if (map[nRow][nCol] > 0 && !visited[nRow][nCol]) {
                dfs(nRow, nCol);
            }
        }
    }

    public void melt() {
        this.time++;

        for (int i = 1; i < row-1; i++) {
            Arrays.fill(meltMap[i], 0);
        }

        for (int i = 1; i < row-1; i++) {
            for (int j = 1; j < col-1; j++) {  // 1 ~ 5
                if (map[i][j] > 0) {
                    int zeroCount = 0;
                    for (int[] dir : this.dirs) {
                        if (map[i + dir[0]][j + dir[1]] == 0) zeroCount++;
                    }
                    meltMap[i][j] = zeroCount;
                }
            }
        }

        for (int i = 1; i < row-1; i++) {
            for (int j = 1; j < col-1; j++) {
                if (meltMap[i][j] > 0) {
                    map[i][j] = Math.max(map[i][j] - meltMap[i][j], 0);
                    if (map[i][j] == 0) {
                        iceCount--;
                    }
                }
                if(map[i][j] > 0) {
                    sRow = i;
                    sCol = j;
                }
            }
        }
    }
}