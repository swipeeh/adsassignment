package adsassignment.graph;

import adsassignment.collectionfactory.CollectionFactory;

public interface IGraph {

    /**
     * Adds an edge to your graph, between vertices with elements 'start' and 'end'.
     * Rememer that an edge goes from start to end, but also from end to start.
     * If either 'end' is null, then 'start' will be added as a vertice without edges. And vice versa for 'end'.
     * If both 'start' and 'end' is null, nothing is done.
     * @param start first vertice
     * @param end second vertice
     * @param weight the weight of the edge between vertices 'start' and 'end'
     */
    public void addEdge(String start, String end, double weight);

    /**
     * Adds a single vertice to the graph. It is not connected to any other vertices.
     * @param vertice the vertice to add to the graph
     */
    public void addVertice(String vertice);

    /**
     * Computes the shortest path from vertice 'start' to vertice 'end'.
     * @param start the vertice to start from, cannot be null or empty String
     * @param end the vertice to end at, cannot be null or empty String
     * @return a path object, which containsVertice the path as a list of Strings and a total cost of the path
     */
    public Path computePath(String start, String end);

    /**
     * Verify whether an edge exists between two elements.
     * @param start first vertice, cannot be null or empty String
     * @param end second vertice, cannot be null or empty String
     * @return true if there is an edge. False if there is no edge, or if one or both of the elements does not exist.
     */
    public boolean containsEdge(String start, String end);

    /**
     * used to get the weight of the edge between vertice 'start' and vertice 'end'
     * @param start first vertice, cannot be null or empty String
     * @param end second vertice, cannot be null or empty String
     * @return the weight of the edge between 'start' and 'end'. If there is no edge, or one or both of the vertices
     * does not exist, return -1
     */
    public double getWeight(String start, String end);

    /**
     * Used to get the number of elements/vertices in the graph
     * @return the number of elements in the graph
     */
    public int size();

    /**
     * Used to get the number of edges in the graph
     * @return the number of edges
     */
    public int numberOfEdges();

    /**
     * Should return a string representation of the graph.
     * E.g.: (A,B,3) (A,C,5) (C,B,2)
     */
    public void printGraph();

    /**
     * This method creates a minimum spanning tree as a new graph. That graph is then returned.
     * @return a new graph, which represents a minimum spanning tree.
     */
    public IGraph computeMinSpanningTree();

    /**
     * Is the graph connected?
     * @return true if the graph is connected, false otherwise.
     */
    public boolean isConnected();

    /**
     * Check whether the specified element is in the graph
     * @param element the element to look for
     * @return true if the graph containsVertice the specified element, otherwise false
     */
    public boolean containsVertice(String element);

    /**
     * Removes the edge between parameters a and b. If there is no edge, do nothing.
     * @param a first vertice
     * @param b second vertice
     */
    public void removeEdge(String a, String b);

    /**
     * Removes the specified vertice, along with all edges going to and from this vertice
     * @param a the vertice to remove
     */
    public void removeVertice(String a);

    /**
     * This method will empty the graph
     */
    public void clear();

    /**
     * Setter method to set the parameters for the CollectionFactory
     * @param pqType the priority queue type to use
     * @param setType the set type to use
     */
    void setCollectionsToUse(CollectionFactory.PQType pqType, CollectionFactory.SetType setType);
}
