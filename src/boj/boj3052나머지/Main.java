package boj.boj3052나머지;

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

        Set<Integer> key = new HashSet<>();
        for(int i=1; i<=10; i++) key.add(Integer.parseInt(br.readLine()) % 42);

        bw.write(key.size() + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

