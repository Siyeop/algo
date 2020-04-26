package boj.boj11049행렬곱셈순서;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11049

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N;
    static int[][] SIZE;
    static int[][] DP;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. Read quiz
        N = Integer.parseInt(br.readLine());
        SIZE = new int[N+1][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            SIZE[i][0] = Integer.parseInt(st.nextToken());
            SIZE[i][1] = Integer.parseInt(st.nextToken());
        }


        // 2. Calc DP
        dp();
        bw.write(DP[1][N] + "");

        bw.flush();
        bw.close();
        br.close();
    }

    static void dp() {
        DP = new int[N+1][N+1];
        int first;
        int second;
        for(int gap=1; gap<N; gap++) {
            for(int i=1; i<=N-gap; i++) {
                if(gap==1) {
                    DP[i][i+gap] = SIZE[i][0] * SIZE[i][1] * SIZE[i+gap][1];
                } else {
                    DP[i][i+gap] = Integer.MAX_VALUE;
                    for(int j=0; j<gap; j++) {
                        DP[i][i+gap] = Math.min(DP[i][i+gap],
                                DP[i][i+j] + DP[i+j+1][i+gap] + (SIZE[i][0] * SIZE[i+j][1] * SIZE[i+gap][1]));
                    }
                }
            }
        }
    }
}
