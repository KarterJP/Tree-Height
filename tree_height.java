import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int[] parents;
        Node[] nodes;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = in.nextInt();
            }
        }

        int computeHeight() {
            int root = 0;
            int maxHeight = 0;
            nodes = new Node[n];
            Queue<Node> queue = new LinkedList<>();

            // Creating Nodes
            for (int i = 0; i < parents.length; i++) {
                nodes[i] = new Node(i);
            }

            // Populating Nodes with children
            for (int i = 0; i < parents.length; i++) {
                int parentValue = parents[i];

                if (parentValue == -1) {
                    root = i;
                } else {
                    nodes[parentValue].addChild(nodes[i]);
                }

            }

            // Traversing tree
            queue.add(nodes[root]);
            nodes[root].level = 1;
            while (!queue.isEmpty()) {
                Node parent = queue.remove();

                if (parent.hasChildren()) {
                    for (Node child : parent.children) {
                        queue.add(child);
                        child.level = parent.level + 1;
                    }
                }

                // Check max
                maxHeight = Math.max(maxHeight, parent.level);
            }

            return maxHeight;
        }

    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }
    TreeHeight tree;
    public void run() throws IOException {
        tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }

    class Node {
        Queue<Node> children;
        int level;
        int value;
        Node(int value) {
            this.value = value;
            this.children = new LinkedList<>();
            this.level = 0;
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }
    }
}
