package adsassignment.priorityqueue.linearPriorityqueue;

public class PQNode<T> implements Comparable<PQNode<T>> {

    public T element;
    public double priority;
    public int secPriority;
    private PQNode<T> next;

    public PQNode(T element, double priority, int secPriority) {
        this.element = element;
        this.priority = priority;
        this.secPriority = secPriority;
        next = null;
    }
    
    public PQNode<T> getNext() {
    	return next;
    }
    
    public void setNext(PQNode<T> node) {
    	next = node;
    }
    /**
     * Returns less than zero, if this PQNode's priority is less than the parameter PQNode. Zero if they are the same.
     * Larger than zero if the parameter PQNode is less than this PQNode.
     * @param tpqNode node to compare against
     * @return integer based on the comparison
     */
    @Override
    public int compareTo(PQNode<T> tpqNode) {
        if(priority == tpqNode.priority) {
            return secPriority - tpqNode.secPriority;
        }
        return priority < tpqNode.priority ? -1 : 1;
    }
    
    public boolean secIsBigger(PQNode<T> node) {
    	if(secPriority > node.getSecPriority()) {
    		return true;
    	}
    	return false;
    }
    
    public int getSecPriority() {
    	return secPriority;
    }
    
    public T getElement() {
    	return element;
    }
    public boolean equals(Object o) {
        if(!(o instanceof PQNode)) {
            return false;
        }
        return element.equals(((PQNode<T>)o).element);
    }
}
