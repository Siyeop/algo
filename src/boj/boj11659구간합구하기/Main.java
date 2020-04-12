package boj.boj11659구간합구하기;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11659

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N; // 수의 개수
    static int M; // 합을 구해야 하는 횟수
    static int[] DP; // 미리 계산해놓는 합의 수

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        // 1. 첫번째수부터 i번째 수까지 더한 값을 DP배열에 저장
        DP = new int[N+1];
        DP[0] = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            DP[i] = DP[i-1] + Integer.parseInt(st.nextToken());
        }

        // 2. 계산
        int from, to;
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            from = DP[Integer.parseInt(st.nextToken()) - 1];
            to = DP[Integer.parseInt(st.nextToken())];
            bw.write(to - from + "\n");
        }

        // 3. 출력
        bw.flush();
        bw.close();
        br.close();
    }
}
