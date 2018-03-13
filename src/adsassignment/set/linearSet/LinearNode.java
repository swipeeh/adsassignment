package adsassignment.set.linearSet;

import java.io.Serializable;

public class LinearNode<T> implements Serializable{

	private T element;
	private LinearNode<T> next;
	
	public LinearNode(){

	}
	
	public LinearNode(T element){
		this.element = element;
		next = null;
	}
	
	public LinearNode(T element, LinearNode<T> next){
		this.element = element;
		this.next = next;
	}
	
	public LinearNode<T> getNext(){
		return next;
	}
	
	public void setNext(LinearNode<T> node){
		next = node;
	}
	
	public T getElement(){
		return element;
	}
	
	public void setElement(T element){
		this.element = element;
	}
}
