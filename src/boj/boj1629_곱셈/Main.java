package boj.boj1629_곱셈;

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

        int A = Integer.parseInt(st.nextToken()); // A 를
        int B = Integer.parseInt(st.nextToken()); // B 제곱해서
        int C = Integer.parseInt(st.nextToken()); // C 로 나눈 나머지를 구하시오

        String binary = convToBinary(B);

        long result = 1L;
        long base = A;
        int start = binary.length()-1;
        for(int i=start; i>=0; i--) {
            if(i!=start) base = (base * base) % C;
            if(binary.charAt(i) == '1') result = (result * base) % C;
        }

        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static String convToBinary(int num) {

        StringBuilder sb = new StringBuilder();

        while(num > 0) {
            sb.append(num%2);
            num >>= 1;
        }

        return sb.reverse().toString();
    }
}

