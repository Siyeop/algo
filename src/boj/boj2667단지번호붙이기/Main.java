package boj.boj2667단지번호붙이기;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int N = Integer.parseInt(br.readLine().trim());

        Graph graph = new Graph(N);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            graph.setMap(i, st.nextToken());
        }

        graph.startDfs();
        graph.printDfsResult();


        bw.flush();
        bw.close();
        br.close();
    }
}


class Graph {

    int size;
    boolean[][] map;
    boolean[][] visited;
    int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    List<Integer> list = new ArrayList<>();


    public Graph(int size) {
        this.size = size;
        this.map = new boolean[size + 2][size + 2];
        this.visited = new boolean[size + 2][size + 2];
    }

    public void setMap(int num, String value) {
        for (int i = 0; i < value.length(); i++) {
            this.map[num][i + 1] = (value.charAt(i) == '1');
        }
    }

    public void startDfs() {
        for (int i = 1; i <= this.size; i++) {
            for (int j = 1; j <= this.size; j++) {
                if (map[i][j] && !visited[i][j]) {
                    int result = dfs(i, j);
                    this.list.add(result);
                }
            }
        }
    }

    public int dfs(int row, int col) {

        visited[row][col] = true;
        int count = 1;

        for (int[] dir : direction) {
            if(map[row + dir[0]][col + dir[1]] && !visited[row + dir[0]][col + dir[1]]) {
                count += dfs(row + dir[0], col + dir[1]);
            }
        }
        return count;
    }

    public void printDfsResult() {
        Collections.sort(list);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }
}

