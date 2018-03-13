package adsassignment.priorityqueue.linearPriorityqueue;

import adsassignment.priorityqueue.IPriorityQueue;

public class LinearPQ<T> implements IPriorityQueue<T> {

	private PQNode<T> front;
	private int size;
	private int running = 0;
	
    public LinearPQ() {
    	front = null;
		size = 0;
    }

    @Override
    public void add(T element, double priority) {
    	if(element == null) {
    		
    	}else if(size == 0){
    		PQNode<T> n = new PQNode<T>(element, priority, ++running);
    		front = n;
    		size++;
    	}else {
    		PQNode<T> n = new PQNode<T>(element, priority, ++running);
    		PQNode<T> current = front;
    		
    		if(current.compareTo(n) == 0) {
				if(current.secIsBigger(n)) {
					/*n.setNext(current.getNext());
					current.setNext(n);
					size++;*/
					n.setNext(current);
					front = n;
					size++;
				}
			}
    		else if(current.compareTo(n) > 0) {
    			n.setNext(current);
				front = n;
				size++;
    		}
    		else {
    			for (int i = 0; i < size; i++) {
    				if(current.getNext()!= null && current.getNext().compareTo(n) < 0) {
    					current = current.getNext();
    				}
    				else if(current.getNext() != null && current.getNext().compareTo(n) == 0) {
    					if(current.getNext().secIsBigger(n)) {
    						n.setNext(current.getNext());
    						current.setNext(n);
    						size++;
    						break;
    					}
    					else {
    						current = current.getNext();
    					}
    				}
    				else {
    					n.setNext(current.getNext());
    					current.setNext(n);
    					size++;
    					break;
    				}
    			}
    		}
    		
    		
    	}
    }

    @Override
    public T getNext() {
    	T temp = front.getElement();
    	front = front.getNext();
    	size--;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void printPQ() {
    	PQNode<T> current = front;
    	String str = "";
    	for (int i = 0; i < size; i++) {
    		str += current.element.toString();
    		current = current.getNext();
		}
    	System.out.println(str);
    }

    @Override
    public boolean contains(T element) {
    	if(element == null) {
    		throw new IllegalArgumentException("Element cannot be null.");
    	}
    	PQNode<T> current = front;
    	for (int i = 0; i < size; i++) {
			if(current.getElement().equals(element)) {
				return true;
			}
			current = current.getNext();
		}
    	return false;
    }

    @Override
    public void clear() {
    	size = 0;
        front = null;
    }
}
