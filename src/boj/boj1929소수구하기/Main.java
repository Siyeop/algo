package boj.boj1929소수구하기;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1929

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N, M; // N <= M <= 1,000,000
    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. Sieve of eratosthenes
        isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        for(int i=2; i<=500000; i++) {
            if(isPrime[i]) {
                for(int j=i+i; j<=1000000; j=j+i) {
                    isPrime[j] = false;
                }
            }
        }

        // 2. 정답계산
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=N; i<=M; i++) {
            if(isPrime[i]) bw.write(i+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
