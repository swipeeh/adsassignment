package adsassignment.graph;

public class Edge {

	public Vertice fromVertice;
	public Vertice toVertice;
	public double weight;
	
	public Edge(Vertice fromVertice, Vertice toVertice, double weight) {
		this.fromVertice = fromVertice;
		this.toVertice = toVertice;
		this.weight = weight;
	}
}