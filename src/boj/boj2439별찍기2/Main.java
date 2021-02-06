package boj.boj2439별찍기2;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N-i; j++) System.out.print(" ");
            for(int j=1; j<=i; j++) System.out.print("*");
            System.out.println();
        }

        br.close();
    }
}
