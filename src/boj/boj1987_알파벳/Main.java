package boj.boj1987_알파벳;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static char[][] map;
    static int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int R, C;


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(boj.Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 1. 문제 읽기
        st = new StringTokenizer(br.readLine().trim());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int i=0; i<R; i++) {
            String row = br.readLine().trim();
            for(int j=0; j<C; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        // 2. 풀이
        int result = dfs(0, 0, new HashSet<>());

        // 3. 출력
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int row, int col, Set<Character> used) {

        char curr = map[row][col];
        if(used.contains(curr)) return 0;
        used.add(curr);

        int result = 0;
        for(int[] dir : dirs) {
            int nRow = row+dir[0];
            int nCol = col+dir[1];
            if(nRow<0 || nRow>=R || nCol<0 || nCol>=C) continue;
            result = Math.max(dfs(nRow, nCol, used), result);
        }

        used.remove(curr);
        return result+1;
    }
}

