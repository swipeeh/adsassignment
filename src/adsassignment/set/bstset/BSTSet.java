package adsassignment.set.bstset;

import adsassignment.set.ITreeSet;


public class BSTSet<T extends Comparable<T>> implements ITreeSet<T> {

	private Node<T> root;
	private int size;

    public BSTSet() {
    size = 0;	
    }

    @Override
    public boolean isBalanced() {
        return false;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public String inOrderToString() {
        return null;
    }

    @Override
    public T getLeftChildElementOf(T parent) {
        return null;
    }

    @Override
    public T getRightChildElementOf(T parent) {
        return null;
    }

    @Override
    public T getRootElement() {
        return null;
    }

    @Override
    public void add(T element) {
 
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void printSet() {
        
    }
	
	
	// The below three methods can be used to print a nice representation of the tree. Assuming you're using a linked structure. 
	// You may have to correct some method and variable names.
/*
    protected void prettyPrinter() {
        int height = getHeight();
        int maxLeaves = (int) Math.pow(2, height);

        int maxWidth = (maxLeaves * 4) - 3;

        Queue<Node> q1 = new ArrayDeque<Node>();
        Queue<Node> q2 = new ArrayDeque<Node>();
        q1.add(root);
        for (int h = 0; h <= height; h++) {
            Queue<Node> currentQ = q1.size() > 0 ? q1 : q2;
            Queue<Node> nextQ = q1.size() > 0 ? q2 : q1;

            String nodes = "";
            int ws = maxWidth - (int) Math.pow(2, h);
            int ms = (int) (ws / Math.pow(2, h) + height / Math.pow(2, h));
            int is = (int) ((ms - 3) / 2);
            int numberOfSpaces = is;
            nodes = addLetters(" ", numberOfSpaces, nodes);
            String edges = "";
            edges = addLetters(" ", numberOfSpaces / 2 - 1, edges);
            int size = currentQ.size();
            int prevExtraDigitCount = 0;
            for (int i = 0; i < size; i++) {
                Node n = currentQ.poll();

                if (n.element != null) {
                    nodes += n.element;
                    prevExtraDigitCount = String.valueOf(n.element).length() - 1;
                } else {
                    nodes += " ";
                    prevExtraDigitCount = 0;
                }
                ws = maxWidth - (int) Math.pow(2, h);
                ms = (int) (ws / Math.pow(2, h) + height / Math.pow(2, h));
                numberOfSpaces = ms;
                nodes = addLetters(" ", numberOfSpaces - prevExtraDigitCount, nodes);
                edges = edges + " ";
                if (n.left != null) {
                    edges = edges + ".";
                    edges = addLetters("-", is / 2 - 1, edges);
                } else {
                    edges = addLetters(" ", is / 2, edges);
                }
                if (n.left != null && n.right != null) {
                    edges = edges + "^";
                } else if (n.left != null) {
                    edges = edges + "´";
                } else if (n.right != null) {
                    edges = edges + "`";
                } else {
                    edges = edges + " ";
                }
                if (n.right != null) {
                    edges = addLetters("-", is / 2 - 1, edges);
                    edges = edges + ".";
                } else {
                    edges = addLetters(" ", is / 2, edges);
                }
                edges = edges + " ";
                edges = addLetters(" ", is + 1, edges);
                if (n.element != null) {
                    nextQ.add(n.left != null ? n.left : new Node());
                    nextQ.add(n.right != null ? n.right : new Node());
                } else {
                    nextQ.add(new Node());
                    nextQ.add(new Node());
                }
            }
            System.out.println(nodes);
            System.out.println(edges);
        }
    }

    protected String addLetters(String letter, int count, String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < count; i++) {
            sb.append(letter);
        }
        return sb.toString();
    }

    
	*/

    @Override
    public void clear() {
    	root = null;
        size = 0;
    }
	
	@Override
    public void addRange(T... range) {
		 for (T t : range) {
	            add(t);
	        }  
    }
	
	// below is another version of the prettyPrinter method, this one should work with arrays.
	/*
	 private void printArrayTree() {
        int height = getHeight();
        int maxLeaves = (int) Math.pow(2, height);

        int maxWidth = (maxLeaves * 4) - 3;

        Queue<Integer> q1 = new ArrayDeque<Integer>();
        Queue<Integer> q2 = new ArrayDeque<Integer>();
        q1.add(0);
        for (int h = 0; h <= height; h++) {
            Queue<Integer> currentQ = q1.size() > 0 ? q1 : q2;
            Queue<Integer> nextQ = q1.size() > 0 ? q2 : q1;

            String nodes = "";
            int ws = maxWidth - (int) Math.pow(2, h);
            int ms = (int) (ws / Math.pow(2, h) + height / Math.pow(2, h));
            int is = (int) ((ms - 3) / 2);
            int numberOfSpaces = is;
            nodes = addLetters(" ", numberOfSpaces, nodes);
            String edges = "";
            edges = addLetters(" ", numberOfSpaces / 2 - 1, edges);
            int size = currentQ.size();
            int prevExtraDigitCount = 0;
            for (int i = 0; i < size; i++) {
                Integer n = currentQ.poll();

                if (n != -1 && tree[n] != null) {
                    nodes += tree[n];
                    prevExtraDigitCount = String.valueOf(tree[n]).length() - 1;
                } else {
                    nodes += " ";
                    prevExtraDigitCount = 0;
                }
                ws = maxWidth - (int) Math.pow(2, h);
                ms = (int) (ws / Math.pow(2, h) + height / Math.pow(2, h));
                numberOfSpaces = ms;
                nodes = addLetters(" ", numberOfSpaces - prevExtraDigitCount, nodes);
                edges = edges + " ";
                if (n != -1 && left(n) < tree.length && tree[left(n)] != null) {
                    edges = edges + ".";
                    edges = addLetters("-", is / 2 - 1, edges);
                } else {
                    edges = addLetters(" ", is / 2, edges);
                }
                if (n != -1 && right(n) < tree.length && tree[left(n)] != null && tree[right(n)] != null) {
                    edges = edges + "^";
                } else if (n != -1 && left(n) < tree.length && tree[left(n)] != null) {
                    edges = edges + "´";
                } else if (n != -1 && right(n) < tree.length && tree[right(n)] != null) {
                    edges = edges + "`";
                } else {
                    edges = edges + " ";
                }
                if (n != -1 && right(n) < tree.length && tree[right(n)] != null) {
                    edges = addLetters("-", is / 2 - 1, edges);
                    edges = edges + ".";
                } else {
                    edges = addLetters(" ", is / 2, edges);
                }
                edges = edges + " ";
                edges = addLetters(" ", is + 1, edges);
                if (n != -1 && tree[n] != null) {
                    if (left(n) < tree.length) {
                        nextQ.add(tree[left(n)] != null ? left(n) : -1);
                    } else {
                        nextQ.add(-1);
                    }

                    if (right(n) < tree.length) {
                        nextQ.add(tree[right(n)] != null ? right(n) : -1);
                    } else {
                        nextQ.add(-1);
                    }
                } else {
                    nextQ.add(-1);
                    nextQ.add(-1);
                }
            }
            System.out.println(nodes);
            System.out.println(edges);
        }
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * (i + 1);
    }
	*/
}
