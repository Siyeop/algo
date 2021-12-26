package boj.boj2512_예산;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N; // 도시 수, 3 ~ 10,000
    static int[] V; // 도시별 필요예산, 1 ~ 100,000
    static int M; // 총 예산, N ~ 1,000,000,000
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int min = -1; // Binary Search 최소 최대
        int max = -1;

        N = Integer.parseInt(br.readLine().trim());
        V = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<N; i++) {
            V[i] = Integer.parseInt(st. nextToken());
            if(V[i] > max) max = V[i];
        }
        M = Integer.parseInt(br.readLine().trim());
        min = M / N;

        int mid = -1;
        while(min < max) {
            mid = (max + min) / 2;

            int value = calcValue(mid);
            if(value > M) {
                max = mid - 1;
            } else if (value < M) {
                min = mid + 1;
                if(calcValue(min) > M) {
                    min = mid;
                    max = mid;
                    break;
                }
            } else {
                min = mid;
                max = mid;
                break;
            }
        }

        bw.write(min + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int calcValue(int mid) {
        int value = 0;
        for(int i=0; i<N; i++) {
            value += Math.min(mid, V[i]);
        }
        return value;
    }
}

