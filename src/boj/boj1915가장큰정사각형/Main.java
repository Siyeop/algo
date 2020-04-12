package boj.boj1915가장큰정사각형;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1915

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M; // N x M 행렬
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DP = new int[N+1][M+1];

        String row;
        int colValue;
        int prevValue;
        int maxValue = 0;
        for(int i=1; i<=N; i++) {
            row = new StringTokenizer(br.readLine()).nextToken();

            for(int j=1; j<=M; j++) {
                colValue = row.charAt(j-1) - '0';
                if(colValue == 0) {
                    DP[i][j] = 0;
                } else {
                    prevValue = Math.min(DP[i-1][j-1], DP[i-1][j]);
                    prevValue = Math.min(prevValue, DP[i][j-1]);
                    DP[i][j] = prevValue + 1;
                    if(DP[i][j] > maxValue) {
                        maxValue = DP[i][j];
                    }
                }
            }
        }
        bw.write(maxValue * maxValue + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
