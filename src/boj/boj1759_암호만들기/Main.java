package boj.boj1759_암호만들기;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static Deque<Character> deque;

    static int L;
    static int C;
    static char[] chars;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 문제 읽기
        st = new StringTokenizer(br.readLine().trim());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        chars = new char[C];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<C; i++) chars[i] = (st.nextToken().charAt(0));

        // 2. 풀이
        // 2-1. 정렬
        Arrays.sort(chars);
        deque = new ArrayDeque<>();

        // 2-2. 백트래킹
        for(int i=0; i<=C-L; i++) {
            bt(chars[i], i);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void bt(char c, int idx) throws Exception {

        deque.addLast(c);

        if(deque.size() == L) {
            int aeiou = 0;
            if (deque.contains('a')) aeiou++;
            if (deque.contains('e')) aeiou++;
            if (deque.contains('i')) aeiou++;
            if (deque.contains('o')) aeiou++;
            if (deque.contains('u')) aeiou++;

            if(aeiou >= 1 && L - aeiou >= 2) {
                printResult();
            }

            deque.removeLast();
            return;
        }

        for(int i=idx+1; i<C; i++) {
            bt(chars[i], i);
        }

        deque.removeLast();
    }

    public static void printResult() throws Exception {
        bw.write(deque.stream().map(String::valueOf).collect(Collectors.joining()) + "\n");
    }
}

