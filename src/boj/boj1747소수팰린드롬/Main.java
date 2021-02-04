package boj.boj1747소수팰린드롬;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    static int N; // 1 ≤ N ≤ 1,000,000
    static boolean[] isFit; // 소수이면서 회문

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 풀이
        // 1-0. 초기화
        isFit = new boolean[2000001];
        Arrays.fill(isFit, true);
        isFit[1] = false;

        // 1-1. 소수
        for(int i=2; i<=1000000; i++) {
            if(isFit[i]) {
                for(int j=i+i; j<=2000000; j=j+i) {
                    isFit[j] = false;
                }
            }
        }

        // 1-2. 팰린드롬 (=회문)
        N = Integer.parseInt(br.readLine());
        for(int i=N; i<2000000; i++) {
            if(isFit[i]) {
                StringBuilder sb = new StringBuilder(Integer.toString(i));
                if(sb.toString().equals(sb.reverse().toString())) { bw.write(i + "\n"); break;}
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
