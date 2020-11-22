package boj.boj1717집합의표현;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static int[] p;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(boj.boj1717집합의표현.Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N+1];
        for(int i=0; i<=N; i++) p[i] = i;

        int type, num1, num2;
        for(int i=0; i<M ;i++) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            num1 = Integer.parseInt(st.nextToken());
            num2  = Integer.parseInt(st.nextToken());
            if(type == 0) union(num1, num2);
            else if(type == 1){
                if(find(num1)==find(num2)) bw.write("YES" +"\n");
                else bw.write("NO" +"\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static void union(int num1, int num2) {
        if(find(num1) != find(num2)) {
            if(find(num1) < find(num2)) p[find(num2)] = p[num1];
            else p[find(num1)] = p[num2];
        }
    }
    private static int find(int num1) {
        if(p[num1] != num1) p[num1] = find(p[num1]);
        return p[num1];
    }
}
