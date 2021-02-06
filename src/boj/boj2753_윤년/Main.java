package boj.boj2753_윤년;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int year = Integer.parseInt(br.readLine());
        if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) bw.write(1 + "\n");
        else bw.write(0 + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

