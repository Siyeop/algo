package boj.boj2579계단오르기;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

// https://www.acmicpc.net/problem/2579

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    static int N;
    static int[] POINT;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        readPoint();
        calcDp();
        int result = calcResult();

        bw.write(result + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static void readPoint() throws IOException {
        POINT = new int[N+1];
        for(int i=1; i<=N; i++) {
            POINT[i] = Integer.parseInt(br.readLine());
        }
    }

    static void calcDp() {
        DP = new int[N+1][3];
        DP[0][0] = 0; DP[0][1] = 0; DP[0][2] = 0;

        for(int i=1; i<=N; i++) {
            DP[i][0] = Math.max(DP[i-1][1], DP[i-1][2]);
            DP[i][1] = DP[i-1][0] + POINT[i];
            DP[i][2] = DP[i-1][1] + POINT[i];
        }
    }

    static int calcResult() {
        return Math.max(DP[N][1], DP[N][2]);
    }
}
