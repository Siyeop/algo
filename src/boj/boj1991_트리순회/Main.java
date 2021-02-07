package boj.boj1991_트리순회;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(URLDecoder.decode(Main.class.getResource("").getPath(), "UTF-8") + "sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Graph graph = new Graph(N);
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            graph.addNode(st.nextToken().charAt(0), st.nextToken().charAt(0), st.nextToken().charAt(0));
        }

        // 1. pre order traversal
        graph.preOrderTraversal('A');
        bw.write("\n");
        // 2. in order traversal
        graph.inOrderTraversal('A');
        bw.write("\n");
        // 3. post order traversal
        graph.postOrderTraversal('A');
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

class Graph {
    int size;
    Map<Character, Node> nodeMap;

    public Graph(int size) {
        this.size = size;
        this.nodeMap = new HashMap<>();
    }

    public void addNode(char root, char left, char right) {
        Node target;
        if(nodeMap.containsKey(root)) target = nodeMap.get(root);
        else target = new Node(root);
        if(left != '.') target.left = new Node(left);
        if(right != '.') target.right = new Node(right);
        nodeMap.put(root, target);
    }

    public void preOrderTraversal(char num) throws Exception {
        Node node = nodeMap.get(num);
        Main.bw.write(node.num);
        if(node.left != null) preOrderTraversal(node.left.num);
        if(node.right != null) preOrderTraversal(node.right.num);
    }

    public void inOrderTraversal(char num) throws Exception {
        Node node = nodeMap.get(num);
        if(node.left != null) inOrderTraversal(node.left.num);
        Main.bw.write(node.num);
        if(node.right != null) inOrderTraversal(node.right.num);
    }

    public void postOrderTraversal(char num) throws Exception {
        Node node = nodeMap.get(num);
        if(node.left != null) postOrderTraversal(node.left.num);
        if(node.right != null) postOrderTraversal(node.right.num);
        Main.bw.write(node.num);
    }
}

class Node {
    char num;
    Node left;
    Node right;

    public Node(char num) {
        this.num = num;
    }
}
