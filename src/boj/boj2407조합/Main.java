package boj.boj2407조합;

import java.io.*;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 1. nCn 계산 (5 ≤ n ≤ 100, 5 ≤ m ≤ 100, m ≤ n)

        // 1-1. 파스칼의 삼각형에 넣을 배열 선언, 결과 값이 long 범위를 벗어나므로 BigInteger 사용
        int MAX_N = 100;
        BigInteger[][] pascal = new BigInteger[MAX_N+1][MAX_N+1];
        for(int i=0; i<=MAX_N; i++) pascal[i] = new BigInteger[MAX_N+1];

        // 1-2. DP base 세팅
        for(int i=0; i<=MAX_N; i++) pascal[i][0] = BigInteger.ONE;

        for(int i=1; i<=MAX_N; i++)
            for(int j=1; j<=i; j++)
                pascal[i][j] =  pascal[i-1][j-1].add( pascal[i-1][j] == null ? BigInteger.ZERO : pascal[i-1][j]);


        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        bw.write(pascal[N][M] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

