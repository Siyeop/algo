package boj.boj9663Nqueen;

import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;

    static int[] visited = new int[15];
    static int N;

    public static void main(String[] args) throws Exception {
        Long startTime = System.currentTimeMillis();
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int result = dfs(0);
        bw.write(result + "");
        Long endTime = System.currentTimeMillis();
        // System.out.println(endTime - startTime + "mills");

        bw.flush();
        bw.close();
        br.close();
    }

    static int dfs(int visitedSize) {
        if (visitedSize > 0 && isWrongPosition(visitedSize)) {
            return 0;
        }
        if (visitedSize == N) {
            return 1;
        }

        int ret = 0;
        for (int i = 1; i <= N; i++) {
            visited[visitedSize] = i;
            ret += dfs(visitedSize + 1);
        }
        return ret;
    }

    static boolean isWrongPosition(int visitedSize) {
        int lastIdx = visitedSize - 1;
        int lastPosition = visited[lastIdx];

        for (int idx = 0; idx < lastIdx; idx++) {
            int idxPosition = visited[idx];
            if (idxPosition == lastPosition) {
                return true;
            }

            int gapIdx = lastIdx - idx;
            if (Math.abs(idxPosition - lastPosition) == gapIdx) {
                return true;
            }
        }
        return false;
    }
}
