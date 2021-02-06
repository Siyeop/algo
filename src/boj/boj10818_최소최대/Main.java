package boj.boj10818_최소최대;

import java.io.*;
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

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int min = 1000000;
        int max = -1000000;
        for(int i=1; i<=N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num < min) min = num;
            if(num > max) max = num;
        }

        bw.write(min + " " + max);

        bw.flush();
        bw.close();
        br.close();
    }
}

