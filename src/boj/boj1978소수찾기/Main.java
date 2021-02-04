package boj.boj1978소수찾기;

import java.io.*;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N: 100개 이하, 1000이하의 수
        boolean[] isPrime = new boolean[1001];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;

        // 에라토스테네스의 체
        for(int i=2; i<=500; i++) {
            if(isPrime[i]) {
                for (int j = i + i; j <= 1000; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        int result = 0;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            if(isPrime[Integer.parseInt(st.nextToken())]) {
                result++;
            }
        }
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

