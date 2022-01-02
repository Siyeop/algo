package boj.boj2636_치즈;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph graph = new Graph(N, M);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph.map[i][j] = Integer.parseInt(st.nextToken());
                if(graph.map[i][j] == 1) graph.cheeseCount++;
            }
        }

        while(graph.cheeseCount > 0) {
            graph.melt();
        }

        bw.write(graph.time + "\n");
        bw.write(graph.prevCheeseCount + "\n");

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
    int cheeseCount;
    int prevCheeseCount;
    int time;
    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public Graph(int row, int col) {
        this.map = new int[row][col];
        this.meltMap = new int[row][col];
        this.visited = new boolean[row][col];
        this.cheeseCount = 0;
        this.prevCheeseCount = 0;
        this.time = 0;
        this.row = row;
        this.col = col;
    }

    public void melt() {
        this.prevCheeseCount = this.cheeseCount;
        this.time++;
        this.clearVisited();
        this.dfs(0, 0);
        this.applyMeltMap();
    }

    public void clearVisited() {
        for(int i=0; i<this.row; i++) {
            Arrays.fill(this.visited[i], false);
            Arrays.fill(this.meltMap[i], 0);
        }
    }

    public void applyMeltMap() {
        for(int i=1; i<row-1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if(meltMap[i][j] == 1) {
                    map[i][j] = 0;
                    this.cheeseCount--;
                }
            }
        }
    }

    public void dfs(int row, int col) {
        this.visited[row][col] = true;

        for(int[] dir : dirs) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow < 0 || nRow >= this.row || nCol < 0 || nCol >= this.col) continue;

            if(!visited[nRow][nCol]) {
                if(map[nRow][nCol] == 1) {
                    this.meltMap[nRow][nCol] = 1;
                    this.visited[row][col] = true;
                } else {
                    this.dfs(nRow, nCol);
                }
            }
        }
    }
}