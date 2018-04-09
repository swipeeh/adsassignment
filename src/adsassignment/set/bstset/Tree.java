package adsassignment.set.bstset;

public class Tree<T extends Comparable<T>> {
	private T[] tree;
	
	public Tree() {
		tree = (T[]) new Comparable[11];
	}
}
