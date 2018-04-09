package adsassignment.set;



public interface ITreeSet<T extends Comparable<T>> extends ISet<T> {

    /**
     * Is the tree balanced
     * @return true if the tree is balanced, otherwise false
     */
    public boolean isBalanced();

    /**
     * The height of the tree.
     * Remember a tree with only a root has height 0, and a tree with 0 elements has height -1
     * @return the height of the tree
     */
    public int getHeight();

    /**
     * @return a string representation of an inorder traversal, in the exact format: {1, 2, 4, 5, 7, 8}
     */
    public String inOrderToString();

    /**
     * This mehod will return the element in the left child of the input 'parent'
     * @param parent the element to look for
     * @return the left child of the parameter
     */
    public T getLeftChildElementOf(T parent);

    /**
     * This mehod will return the element in the right child of the input 'parent'
     * @param parent the element to look for
     * @return the right child of the parameter
     */
    public T getRightChildElementOf(T parent);

    /**
     * Get the element at the root of the tree
     * @return the root element of the tree. Null if the tree is empty.
     */
    public T getRootElement();

}
