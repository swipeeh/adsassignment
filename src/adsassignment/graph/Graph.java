package adsassignment.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import adsassignment.collectionfactory.CollectionFactory;
import adsassignment.collectionfactory.CollectionFactory.PQType;
import adsassignment.collectionfactory.CollectionFactory.SetType;
import adsassignment.priorityqueue.IPriorityQueue;
import adsassignment.priorityqueue.linearPriorityqueue.LinearPQ;
import adsassignment.set.*;

public class Graph implements IGraph {

	public List<Vertice> vertices;
	
    /**
     * You should use the variable pqType to change between LinearPQ and HeapPQ.
     * Possible values are: LINEAR & HEAP
     */
    private CollectionFactory.PQType pqType = CollectionFactory.PQType.LINEAR;

    /**
     * You should use the variable setType to change between LinearSet, BSTSet, and AVLSet.
     * Just change the last LINEAR part to either LINEAR, AVL, or BST
     */
    private CollectionFactory.SetType setType = CollectionFactory.SetType.LINEAR;


    public Graph() {
    	vertices = new LinkedList<>();
    }

    /**
     * This constructor is used mainly for testing purposes. This way you can easily change the collections used
     * by your test methods, if you change your constructor arguments.
     * @param pqt
     * @param st
     */
    public Graph(CollectionFactory.PQType pqt, CollectionFactory.SetType st) {
        this();
        pqType = pqt;
        setType = st;
    }

    @Override
    public void addEdge(String a, String b, double weight) {
    	if(a == null && b == null) {
    		return;
    	}
    	if(weight <= 0) {
    		return;
    	}
    	else if(a == null && b != null) {
    		addVertice(b);
    		return;
    	}
    	else if(b == null && a != null) {
    		addVertice(a);
    		return;
    	}
    	Vertice aVertice = null;
        Vertice bVertice = null;
        for (Vertice vertice: vertices) 
        {
    		if(a.equals(vertice.element)) {
    			aVertice = vertice;
    		}
    		if(b.equals(vertice.element)) {
    			bVertice = vertice;
    		}
    		if (aVertice != null && bVertice != null) {
    			break;
    		}
        }
        if(aVertice == null) {
		aVertice = new Vertice(a);
		vertices.add(aVertice);		
		}
        if(bVertice == null) {
		bVertice = new Vertice(b);
		vertices.add(bVertice);
		}
        
        Target aTarget = new Target(weight, bVertice);
        Target bTarget = new Target(weight, aVertice);
        boolean addA = true;
        boolean addB = true;
        
        for (int i = 0; i < aVertice.edges.size(); i++) {
			if(aVertice.edges.get(i).targetVertice.element.equals(aTarget.targetVertice.element)) {
				addA = false;
				break;
			}
		}
        
        for (int i = 0; i < bVertice.edges.size(); i++) {
			if(bVertice.edges.get(i).targetVertice.element.equals(bTarget.targetVertice.element)) {
				addB = false;
				break;
			}
		}
        
        if(addA) {
        	aVertice.edges.add(aTarget);
        }
        if(addB) {
        	bVertice.edges.add(bTarget);
        }   
    }

    @Override
    public void addVertice(String vertice) {
    	Vertice newVertice = new Vertice(vertice);
    	vertices.add(newVertice);
    }

    private void createEdge(String start, String end, double weight) {
        
    }

    @Override
    public Path computePath(String start, String end) {
    	if(start == null || start == "" || end == null || end == "") {
    		throw new IllegalArgumentException("Argument can not be an empty string or a null.");
    	}
    	
		ISet<Vertice> set = CollectionFactory.getSet(Vertice.class, setType);
		IPriorityQueue<Vertice> queue = CollectionFactory.getPQ(Vertice.class, pqType);
		Vertice current;
		Vertice startVertice = null;
		Vertice endVertice = null;
		int count = 1;
		Map<Vertice, Double> distances = new HashMap<Vertice, Double>();
		Map<Vertice, Vertice> parents = new HashMap<Vertice, Vertice>();
		
		 for (Vertice vertice: vertices) {
	    	if(start.equals(vertice.element)) {
	    		startVertice = vertice;
	    	}
	    	if(end.equals(vertice.element)) {
	    		endVertice = vertice;
	    	}
	    	if (startVertice != null && endVertice != null) {
	    		break;
	    	}
	     }
		parents.put(startVertice, null);
		queue.add(startVertice, 0);
		
		while(!queue.isEmpty()) {
			current = queue.getNext();
			if(end.equals(current.element)) {
				break;
			}
			if(!set.contains(current)) {
				
				set.add(current);
				for (int i = 0; i < current.edges.size(); i++) {
					if(!set.contains(current.edges.get(i).targetVertice)) {
						queue.add(current.edges.get(i).targetVertice, current.edges.get(i).weight);
						
						if(!distances.containsKey(current.edges.get(i).targetVertice) && start.equals(current.element)) {
							distances.put(current.edges.get(i).targetVertice, (current.edges.get(i).weight));
							parents.put(current.edges.get(i).targetVertice, current);
							//System.out.println("A addition: " + (current.edges.get(i).weight));
							
						}
						else if(!distances.containsKey(current.edges.get(i).targetVertice)) {
							distances.put(current.edges.get(i).targetVertice, (current.edges.get(i).weight) + distances.get(current));
							parents.put(current.edges.get(i).targetVertice, current);
							double x = current.edges.get(i).weight + distances.get(current);
							//System.out.println("First addition: " + x);
							
						}
						else if(distances.containsKey(current.edges.get(i).targetVertice) && distances.get(current.edges.get(i).targetVertice) > (current.edges.get(i).weight) + distances.get(current)) {
							distances.put(current.edges.get(i).targetVertice, (current.edges.get(i).weight) + distances.get(current));
							parents.put(current.edges.get(i).targetVertice, current);
							double x = current.edges.get(i).weight + distances.get(current);
							//System.out.println("Another addition: " + x);
							//System.out.println(parents.toString());
						}
					}
				}
			}
		}
		
		//ISet<String> visited = CollectionFactory.getSet(String.class, setType);
        //IPriorityQueue<VerticePair> pq = CollectionFactory.getPQ(VerticePair.class, pqType);
		
		Path path = new Path();
		Vertice loopVertice = endVertice;
		while(loopVertice != null) {
			path.path.add(loopVertice.element);
			loopVertice = parents.get(loopVertice);
		}
		path.cost = distances.get(endVertice);
		Collections.reverse(path.path);
        return path;
    }

    @Override
    public boolean containsEdge(String start, String end) {
    
    	Vertice aVertice = null;
        Vertice bVertice = null;
    	for (Vertice vertice: vertices) 
        {
    		if(vertice.element.equals(start)) {
    			aVertice = vertice;
    		}
    		if(vertice.element.equals(end)) {
    			bVertice = vertice;
    		}
    		if (aVertice != null && bVertice != null) {
    			break;
    		}
        }
    	if(aVertice == null || bVertice == null) {
    		return false;
    	}
    	for (int i = 0; i < aVertice.edges.size(); i++) {
			if(aVertice.edges.get(i).targetVertice.element.equals(end)) {
				return true;
			}
		}
    	return false;
    }

    @Override
    public double getWeight(String start, String end) {
		Vertice aVertice = null;
        Vertice bVertice = null;
    	for (Vertice vertice: vertices) 
        {
    		if(start.equals(vertice.element)){
    			aVertice = vertice;
    		}
    		if(end.equals(vertice.element)){
    			bVertice = vertice;
    		}
    		if (aVertice != null && bVertice != null){
    			break;
    		}
		}
    	if(aVertice == null || bVertice == null) {
    		return -1;
    	}
    	for (int i = 0; i < aVertice.edges.size(); i++) {
			if(aVertice.edges.get(i).targetVertice.element.equals(end)) {
				return aVertice.edges.get(i).weight;
			}
    	}
		return -1;
		}

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public int numberOfEdges() {
    	int number = 0;
        for(int i=0;i<vertices.size();i++) {
        	number += vertices.get(i).edges.size();
        }
        return number/2;
    }

    @Override
    public void printGraph() {
    	StringBuilder s = new StringBuilder();
    	for (Vertice v : vertices) {
    		for (int i = 0; i <v.edges.size(); i++) {
    			s.append("(" + v.element + "," +  v.edges.get(i).targetVertice.element + "," + v.edges.get(i).weight + ") ");
			}
    	}
    	System.out.println(s);
    }

    @Override
    public IGraph computeMinSpanningTree() {
    	IGraph minSpanningTree = new Graph();
		ISet<Vertice> visited = CollectionFactory.getSet(Vertice.class, setType);
		ISet<Vertice> antiCycle = CollectionFactory.getSet(Vertice.class, setType);
        IPriorityQueue<Edge> pq = CollectionFactory.getPQ(Edge.class, pqType);
    	Vertice startVertice = vertices.get(0);
    	pq.add(new Edge(startVertice, null, 0), 0);
    	Edge current;
    	
    	while(!pq.isEmpty()) {
    		current = pq.getNext();
    		if(!antiCycle.contains(current.fromVertice)) {
    			visited.add(current.fromVertice);
    			if(current.toVertice == null ) {
    				minSpanningTree.addEdge(current.fromVertice.element, null, current.weight);
    				antiCycle.add(current.fromVertice);
    			}
    			else {
    				minSpanningTree.addEdge(current.fromVertice.element, current.toVertice.element, current.weight);
    				antiCycle.add(current.fromVertice);
    				System.out.println("dva");
    			}
    			
    			for (int i = 0; i < current.fromVertice.edges.size(); i++) {
					if(!visited.contains(current.fromVertice.edges.get(i).targetVertice)) {
						pq.add(new Edge(current.fromVertice.edges.get(i).targetVertice, current.fromVertice, current.fromVertice.edges.get(i).weight), current.fromVertice.edges.get(i).weight);
					}
				}
    		}
    	}
		System.out.println("Im pickle Riiiick");
        return minSpanningTree;
    }

    @Override
    public boolean isConnected() {
    	IPriorityQueue<Vertice> queue = CollectionFactory.getPQ(Vertice.class, PQType.LINEAR);
    	int count = 1;
    	Vertice v = null;
    	queue.add(vertices.get(0), 0);
    	while(!queue.isEmpty()) {
			v = queue.getNext();
			for (int i = 0; i < v.edges.size(); i++) {
				if(!v.edges.get(i).targetVertice.visited) {
					queue.add(v.edges.get(i).targetVertice, count);
					count++;
					v.edges.get(i).targetVertice.visited = true;
				}
			}
		 }
    	setVisitedFalse();
    	return count-1 == size();
    }
    
    private void setVisitedFalse() {
    	IPriorityQueue<Vertice> queue = CollectionFactory.getPQ(Vertice.class, PQType.LINEAR);
    	int counter = 1;
    	Vertice v = null;
    	queue.add(vertices.get(0), 0);
    	while(!queue.isEmpty()) {
			v = queue.getNext();
			for (int i = 0; i < v.edges.size(); i++) {
				if(v.edges.get(i).targetVertice.visited) {
					queue.add(v.edges.get(i).targetVertice, counter);
					counter++;
					v.edges.get(i).targetVertice.visited = false;
				}
			}
		 }
    }

    @Override
    public boolean containsVertice(String element) {
       for (int i = 0; i < vertices.size(); i++) {
    	   if(vertices.get(i).element.equals(element)) {
			return true;
    	   }
       }
       return false;
    }

    @Override
    public void removeEdge(String a, String b) {
    	Vertice aVertice = null;
        Vertice bVertice = null;
        for (Vertice vertice: vertices) 
        {
    		if(a.equals(vertice.element)) {
    			aVertice = vertice;
    		}
    		if(b.equals(vertice.element)) {
    			bVertice = vertice;
    		}
    		if (aVertice != null && bVertice != null) {
    			break;
    		}
        }
        if(aVertice == null || bVertice == null) {
		return;		
		}
        for (int i = 0; i < aVertice.edges.size(); i++) {
			if(aVertice.edges.get(i).targetVertice.element.equals(b)) {
				aVertice.edges.remove(i);
			}
		}
        for (int i = 0; i < bVertice.edges.size(); i++) {
			if(bVertice.edges.get(i).targetVertice.element.equals(a)) {
				bVertice.edges.remove(i);
			}
		}
    }

    @Override
    public void removeVertice(String a) {
    	Vertice aVertice = null;
        for (Vertice vertice: vertices) 
        {
    		if(vertice.element.equals(a)) {
    			aVertice = vertice;
    		}
    		if (aVertice != null) {
    			break;
    		}
        }
        if(aVertice == null) {
        	return;
        }
        for (int i = aVertice.edges.size()-1; i >= 0; i--) {
			Vertice v = aVertice.edges.get(i).targetVertice;
			removeEdge(a, v.element);
		}
        vertices.remove(vertices.indexOf(aVertice));
    }

    @Override
    public void clear() {
        vertices = new LinkedList<>();
    }

    @Override
    public void setCollectionsToUse(CollectionFactory.PQType pqType, CollectionFactory.SetType setType) {
        this.pqType = pqType;
        this.setType = setType;
    }
    
}

