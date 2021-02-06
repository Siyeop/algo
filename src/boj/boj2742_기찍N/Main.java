package boj.boj2742_기찍N;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for(int i=N; i>=1; i--) bw.write(i + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

