package boj.boj2908상수;

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

        st = new StringTokenizer(br.readLine());
        int n1 = Integer.parseInt(new StringBuilder(st.nextToken()).reverse().toString());
        int n2 = Integer.parseInt(new StringBuilder(st.nextToken()).reverse().toString());

        bw.write(Math.max(n1, n2) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

