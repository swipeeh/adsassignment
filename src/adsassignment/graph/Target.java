package adsassignment.graph;

public class Target {

	public double weight;
	public Vertice targetVertice;
	
	public Target(double weight, Vertice targetVertice) {
		this.weight = weight;
		this.targetVertice = targetVertice;
	}
	
	public String toString() {
		return targetVertice.toString();
	}
}