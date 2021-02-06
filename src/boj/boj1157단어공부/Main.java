    package boj.boj1157단어공부;

    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.InputStreamReader;
    import java.net.URLDecoder;
    import java.util.HashMap;
    import java.util.Iterator;
    import java.util.Map;
    import java.util.StringTokenizer;

    public class Main {
        public static void main(String[] args) throws Exception {
            System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = br.readLine().toUpperCase();
            Map<Character, Integer> countMap = new HashMap<>();
            for(int i=0; i<str.length(); i++) {
                Character c = str.charAt(i);

                if(!countMap.containsKey(c)) countMap.put(c, 0);
                Integer v = countMap.get(c);
                countMap.put(c, ++v);
            }

            char r1 = '-';
            int r2 = 0;

            for(Character c : countMap.keySet()) {
                if(countMap.get(c) > r2) {
                    r1 = c;
                    r2 = countMap.get(c);
                } else if(countMap.get(c) == r2) {
                    r1 = '?';
                }
            }
            System.out.println(r1);
            br.close();
        }
    }
