package boj.boj2475검증수;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int curr;
        int result = 0;
        for(int i=1; i<=5; i++) {
            curr = Integer.parseInt(st.nextToken());
            result += (curr * curr);
        }
        System.out.println(result % 10);


        br.close();
    }
}
