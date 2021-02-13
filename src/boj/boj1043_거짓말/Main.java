package boj.boj1043_거짓말;

import java.io.*;
import java.net.URLDecoder;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int N; // 사람 수
    static int M; // 파티 수
    static int[] group; // 속한 그룹 번호

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 2. 풀이
        // 2-1. 초기화
        group = new int[N+1];
        for(int i=1; i<=N; i++) group[i] = i;
        int[][] mPeople = new int[M+1][]; // 파티 참석자 배열

        // 2-2. 진실 아는사람 배열 union-find
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int[] kPeople = new int[k+1];
        for(int i=1; i<=k; i++) kPeople[i] = Integer.parseInt(st.nextToken());
        for(int i=2; i<=k; i++) union(kPeople[i-1], kPeople[i]);

        // 2-3. 파티 참석자 union-find
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            mPeople[i] = new int[count+1];
            for(int j=1; j<=count; j++) mPeople[i][j] = Integer.parseInt(st.nextToken());
            for(int j=2; j<=count; j++) union(mPeople[i][j-1], mPeople[i][j]);
        }

        // 3. 정답 계산, 파티원 union-find 결과가 진실아는사람 결과와 같지 않으면 말해도 되는 파티
        int result = 0;
        if(k == 0) result = M;
        else for(int i=1; i<=M; i++) if(find(mPeople[i][1]) != find(kPeople[1])) result++;
        bw.write(result + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int num1, int num2) {
        if(find(num1) > find(num2)) group[find(num1)] = find(num2);
        else if(find(num1) < find(num2)) group[find(num2)] = find(num1);
    }

    static int find(int num) {
        if(group[num] == num) return num;
        else return group[num] = find(group[num]);
    }
}

