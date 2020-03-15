package boj.boj1920수찾기;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 주어진 수를 Set에 저장
        int N = Integer.parseInt(br.readLine());
        Set<String> dataSet = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            dataSet.add(st.nextToken());
        }

        // 2. 질의를 Set에서 찾는다...   Arrays.binarySearch(Array명, 찾는내용) -> index 리턴됨
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) {
            if(dataSet.contains(st.nextToken())){
                bw.write("1\n");
            } else {
                bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

