package boj.boj9498_시험성적;

import java.io.*;
import java.net.URLDecoder;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int score = Integer.parseInt(br.readLine());
        char grade;

        if(score >= 90) grade ='A';
        else if(score >= 80) grade ='B';
        else if(score >= 70) grade ='C';
        else if(score >= 60) grade ='D';
        else grade ='F';

        bw.write(grade + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

