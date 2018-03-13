package adsassignment.priorityqueue;

public interface IPriorityQueue<T> {

    /**
     * The priority queue is a queue, where each element has an associated priority: an integer.
     * The next element to come out of the priority queue is the element with the lowest priority value.
     * If element A has priority 3, and element B has priority 5, then A will come out of the queue before B.
     * If two elements has the same priority, the element which was first inserted into the queue will also come first out of the queue.
     */

    /**
     * adds the element 'element' to the priority queue, with a priority of 'priority'
     * @param element the element to add. Cannot be null, in this case the method does nothing.
     * @param priority the priority of the above element
     */
    public void add(T element, double priority);

    /**
     * used to get the next element in the priorty queue
     * @return the next element out of the priorty queue. Return null, if there are no more elements
     */
    public T getNext();

    /**
     * The size of the priority queue
     * @return the number of elements in the priority queue
     */
    public int size();

    /**
     * Is the priority queue empty
     * @return true if there are no elements in the priority queue, otherwise false.
     */
    public boolean isEmpty();

    /**
     * Prints a string representation of the priority queue. The elements must be ordered by priority (and secondary priority)
     * E.g.: (C, 2) (E, 4) (D, 5) (A, 7)
     */
    public void printPQ();

    /**
     * Check whether the priority queue containsVertice a specified element
     * @param element the element to look for. Cannot be null
     * @return true if the specified element is in the priorty queue
     */
    public boolean contains(T element);

    /**
     * This method will empty the queue
     */
    public void clear();
}
