package boj.boj1016_제곱ㄴㄴ수;

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

        // 1 ≤ min ≤ 1,000,000,000,000
        // min ≤ max ≤ min + 1,000,000
        st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        boolean[] answer = new boolean[(int)(max-min+1)];
        Arrays.fill(answer, true);

        for(long i=2; i*i<=max; i++) {
            long square = i * i;
            long start = (min % square == 0) ? min - (min % square) : min - (min % square) + square;

            for(long j=start; j<=max; j+=square) {
                answer[(int)(j-min)] = false;
            }
        }

        int result = 0;
        for(int i=0; i<(max-min+1); i++) {
            if(answer[i]) result++;
        }
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

