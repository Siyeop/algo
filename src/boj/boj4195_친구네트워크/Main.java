package boj.boj4195_친구네트워크;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static Map<String, String> group;
    static Map<String, Integer> groupCount;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine().trim());

        for(int i=1; i<=N; i++) {
            int F = Integer.parseInt(br.readLine().trim());
            group = new HashMap<>();
            groupCount = new HashMap<>();
            for(int j=1; j<=F; j++) {
                st = new StringTokenizer(br.readLine().trim());
                String first = st.nextToken();
                String second = st.nextToken();

                union(first, second);
                bw.write(getGroupCount(first) + "\n");
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }

    public static void union(String s1, String s2) {

        String g1 = find(s1);
        String g2 = find(s2);

        if(!g1.equals(g2)) {

            if(g1.compareTo(g2) < 0) {
                group.put(g2, g1);
                groupCount.put(g1, groupCount.get(g1) + groupCount.get(g2));
            } else {
                group.put(g1, g2);
                groupCount.put(g2, groupCount.get(g2) + groupCount.get(g1));
            }

        }

    }

    public static String find(String s) {
        if(!group.containsKey(s)) {
            group.put(s, s);
            groupCount.put(s, 1);
        }

        if(!group.get(s).equals(s)) {
            String nGroup = find(group.get(s));
            group.put(s, nGroup);
        }

        return group.get(s);
    }

    public static int getGroupCount(String s) {
        return groupCount.get(find(s));
    }

}

