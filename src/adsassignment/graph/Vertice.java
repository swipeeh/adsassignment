package adsassignment.graph;


import java.util.LinkedList;
import java.util.List;

public class Vertice implements Comparable <Vertice> {

	public String element;
	public List<Target> edges;
	public boolean visited;
	
	public Vertice(String element) {
		this.element = element;
		edges = new LinkedList<>();
		visited = false;
	}
	
	public String toString() {
		return element;
	}

	@Override
	public int compareTo(Vertice arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

