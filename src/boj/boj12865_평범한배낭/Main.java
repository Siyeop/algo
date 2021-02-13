package boj.boj12865_평범한배낭;

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

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건 수
        int K = Integer.parseInt(st.nextToken()); // 무게 제한

        int[] w = new int[N+1];
        int[] v = new int[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                if(j-w[i]>=0) dp[i][j] = Math.max(dp[i-1][j-w[i]]+v[i], dp[i-1][j]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        bw.write(dp[N][K] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

