package adsassignment.set.linearSet;

import adsassignment.set.ISet;

public class LinearSet<T extends Comparable<T>> implements ISet<T> {

	private LinearNode<T> front;
	private int size;
	
    public LinearSet() {
    	size = 0;
    }

    @Override
    public void add(T element) {
    	if(element==null)
        {
        throw new IllegalArgumentException("Element can not be null");
        }
    	LinearNode<T> node = new LinearNode<>(element);
    	LinearNode<T> current = front;
    	if(size == 0)
    	{
    		front = node;
    		size++;
    	}
    	else{
    		if(!contains(element)) {
    			node.setNext(current);
	    		front = node;
	    		size++;
    		}
    	}
    }

    @Override
    public boolean contains(T element) {
    	LinearNode<T> current = front;
        for (int i = 0; i < size; i++) {
           if(element.compareTo(current.getElement()) == 0)
           {
              return true;
           }
           else{
              current = current.getNext();
           }
        }
        return false;
    }

    @Override
    public int size() {
    	return size;
    }

    @Override
    public boolean isEmpty() {
    	return size==0;
    }

    @Override
    public void printSet() {
    	LinearNode<T> current = front;
        String str = "{";
        for (int i = 0; i < size; i++) {
        	if(current.getNext() != null) {
        		str+= current.getElement().toString() + ", ";
            	current = current.getNext();
        	}
        	else {
        		str+= current.getElement().toString();
            	current = current.getNext();
        	}
        }
        str+="}";
        System.out.println(str);
    }

    @Override
    public void clear() {
    	front = null;
        size = 0;
    }
	
	@Override
    public void addRange(T... range) {
        for (T t : range) {
            add(t);
        }
    }
}
