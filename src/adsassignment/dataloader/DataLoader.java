package adsassignment.dataloader;

import adsassignment.graph.IGraph;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class DataLoader {

    /**
     * Use this class to load a file containing graph data.
     * The format of the file: each line describes and edge: <verticeA> <verticeB> <weight>
     * E.g.: Skanderborg Horsens 16
     */

    public static void addData(IGraph graph, String fileToLoad) {
		File f = new File(fileToLoad);
        if(!f.exists()) throw new RuntimeException("Could not find file: " + fileToLoad);
        System.out.println("Starting data load..");
        String line;

        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToLoad));
            line = br.readLine();

            while (line != null) {
                if(!line.isEmpty()) {
                    String[] split = line.split(" ");

                    double weight = Double.parseDouble(split[2]);
                    StartEndWeight sew = new StartEndWeight(normalizeName(split[0]), normalizeName(split[1]), weight);
                    graph.addEdge(sew.start, sew.end, sew.weight);
                }
                line = br.readLine();
                count++;
                if(count % 10000 == 0) {
                    //System.out.println("Edges loaded: " + count);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Loaded " + count + " edges into the graph");
    }

    public static String normalizeName(String name) {
        name = name.toLowerCase();
        name = name.replaceFirst(String.valueOf(name.charAt(0)),
                (String.valueOf(name.charAt(0)).toUpperCase()));
        if(name.contains("-")) {
            int idx = name.indexOf("-");
            name = name.replace("-" + name.charAt(idx+1), "-" + String.valueOf(name.charAt(idx+1)).toUpperCase());
        }

        return name;
    }
}
