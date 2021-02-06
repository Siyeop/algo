package boj.boj2675문자열반복;


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
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int repeat = Integer.parseInt(st.nextToken());
            String str = st.nextToken();

            for(int i=0; i<str.length(); i++) for(int j=1; j<=repeat; j++) bw.write(str.charAt(i));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

