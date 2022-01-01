package boj.boj10026_적록색약;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Graph graph = new Graph(N);

        for (int i = 1; i <= N; i++) {
            graph.setMap(i, br.readLine().trim());
        }

        int firstResult = graph.dfsAll(false);
        int secondResult = graph.dfsAll(true);

        bw.write(firstResult + " " + secondResult + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {

    int size;
    char[][] map;
    boolean[][] visited;

    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};


    public Graph(int size) {

        this.size = size;
        map = new char[size + 2][size + 2];

    }

    public void setMap(int lineNum, String line) {
        for (int i = 1; i <= this.size; i++) {
            map[lineNum][i] = line.charAt(i - 1);
        }
    }

    public int dfsAll(boolean isDisabled) {
        this.visited = new boolean[size + 2][size + 2];

        int areaCount = 0;
        for (int i = 1; i <= this.size; i++) {
            for (int j = 1; j <= this.size; j++) {
                if (!this.visited[i][j]) {
                    areaCount++;
                    this.visited[i][j] = true;
                    this.dfs(i, j, isDisabled);
                }
            }
        }

        return areaCount;
    }

    public void dfs(int row, int col, boolean isDisabled) {

        int nRow, nCol;
        for (int[] dir : this.dirs) {
            nRow = row + dir[0];
            nCol = col + dir[1];
            if (!this.visited[nRow][nCol] && this.isSameColor(map[row][col], map[nRow][nCol], isDisabled)) {
                this.visited[nRow][nCol] = true;
                this.dfs(nRow, nCol, isDisabled);
            }
        }
    }

    public boolean isSameColor(char a, char b, boolean isDisabled) {
        if (a == b) {
            return true;
        } else if (isDisabled) {
            return (a == 'R' || a == 'G') && (b == 'R' || b == 'G');
        }

        return false;
    }
}