package boj.boj15652_N과M_4;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static int M; // 사용가능한 숫자
    static int N; // 순열 길이
    static Deque<Integer> usedDeque;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        usedDeque = new ArrayDeque<>();

        for(int i=1; i<=M; i++) backtracking(i);

        bw.flush();
        bw.close();
        br.close();
    }

    static void backtracking(int num) throws Exception {
        usedDeque.addLast(num);

        if(usedDeque.size() == N) {
            printDeque();
            usedDeque.removeLast();
            return;
        }

        for(int i=num; i<=M; i++) backtracking(i);
        usedDeque.removeLast();
    }

    static void printDeque() throws Exception {
        Iterator<Integer> iter = usedDeque.iterator();
        StringBuilder sb = new StringBuilder();
        while(iter.hasNext()) sb.append(iter.next()).append(" ");

        bw.write(sb.toString().trim() + "\n");
    }
}

