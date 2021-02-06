package boj.boj2562최댓값;


import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;


public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(boj.boj2562최댓값.Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int maxValue = 0;
        int maxIndex = 0;
        for(int i=1; i<=9; i++) {
            int curr = Integer.parseInt(br.readLine());
            if(curr > maxValue) {
                maxValue = curr;
                maxIndex = i;
            }
        }

        bw.write(maxValue + "\n" + maxIndex);

        bw.flush();
        bw.close();
        br.close();
    }
}

