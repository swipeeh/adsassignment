package adsassignment.set;

public interface ISet<T extends Comparable<T>> {

    /**
     * The set is a collection, which contains no duplicate elements, and does not promise a specific order.
     * Two elements A and B are equal (duplicate) if both are true: A.equals(B) and B.equals(A).
     * This is simpler set, with fewer methods than the classic set. In this set elements can be added, but nothing can be removed.
     * The set cannot contain 'null' elements.
     */

    /**
     * adds 'element' to the set
     * @param element the element to add to the set. Cannot be null
     */
    public void add(T element);

    /**
     * used to check whether an element exists in the set
     * @param element the element to check for
     * @return true if the set containsVertice the element, false otherwise.
     */
    public boolean contains(T element);

    /**
     * The size of the set
     * @return the number of elements in the set
     */
    public int size();

    /**
     * Is the set empty
     * @return true if there are no elements in the set, otherwise false.
     */
    public boolean isEmpty();

    /**
     * Prints a string representation of the set.
     * E.g.: {A, B, C, D}
     */
    public void printSet();

    /**
     * This method will empty the set
     */
    public void clear();
	
	    /**
     * This method will take a number of parameters of type T. Java are automatically creating an array of type T with
     * the parameters. Call the method like: set.addRange(1, 3, 7, 5); or set.addRange( {5,3,4} );
     * Use the 'range' variable inside the method body like a normal array: range[].
     * This method should just use the add method to add the entire array to the tree.
     * @param range an array of Ts to add to the tree.
     */
    public void addRange(T... range);
}
