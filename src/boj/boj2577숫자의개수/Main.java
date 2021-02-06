package boj.boj2577숫자의개수;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = Integer.toString(Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()));
        int[] count = new int[10];
        for(int i=0; i<str.length(); i++) count[str.charAt(i)-'0']++;
        for(int i=0; i<=9; i++) bw.write(count[i] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

