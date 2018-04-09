package adsassignment.set.bstset;

public class Node<T extends Comparable<T>> {
	public T element;
	public Node<T> left,right,parent;
	public int balance;
	
	public Node(){
		
	}
}
