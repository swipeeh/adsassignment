package adsassignment.graph;

import java.util.LinkedList;
import java.util.List;

public class Path {

    public List<String> path;
    public double cost;

    public Path() {
        path = new LinkedList<>();
    }

    public void printPath() {
        System.out.println("Path from " + path.get(0) + " to " + path.get(path.size() - 1));
        System.out.println("Cost: " + cost);
        System.out.println("Number of steps: " + path.size());
        for (String s : path) {
            System.out.print(s + ", ");
        }
    }
}
