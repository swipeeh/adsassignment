package adsassignment.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import adsassignment.collectionfactory.CollectionFactory;
import adsassignment.dataloader.StartEndWeight;
import adsassignment.graph.Graph;
import adsassignment.graph.IGraph;

public class GraphTest {

    IGraph g;

    @Before
    public void setup(){
        g = new Graph(CollectionFactory.PQType.LINEAR, CollectionFactory.SetType.LINEAR);
    }

    @Test
    public void testConnected() {
        // below method will create the following graph stucture:
        //               (20)              (30)
        //      Skive ----------- Viborg ----------Langaa
        //       |                |   |                |
        //   (22)|    /----------/    |(20)            |
        //       |   |  (25)          |                |(27)
        //      Haderup               Kjellerup        |
        //        |(25)                 |(15)          |
        //      Herning ----------- Silkeborg ------- Aarhus
        //        |(27)      (35)          \    (40)   |
        //      Brande                  (30)\          |(16)
        //         |(14)                     ---\      |
        //       Give--------------\(15)         Skanderborg
        //         |                \--------\       |(15)
        //         \   (20)                   \-Horsens
        //          \---------Vejle ----------/
        //                              (10)

        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }
        assertTrue(g.isConnected());
    }

    @Test
    public void testLonelyIsland() {
        // below method will create the following graph stucture:
        //               (20)              (30)
        //      Skive ----------- Viborg ----------Langaa
        //       |                |   |                |
        //   (22)|    /----------/    |(20)            |
        //       |   |  (25)          |                |(27)
        //      Haderup               Kjellerup        |
        //        |(25)                 |(15)          |
        //      Herning ----------- Silkeborg ------- Aarhus
        //        |(27)      (35)          \    (40)   |
        //      Brande                  (30)\          |(16)
        //         |(14)                     ---\      |
        //       Give--------------\(15)         Skanderborg
        //         |                \--------\       |(15)
        //         \   (20)                   \-Horsens
        //          \---------Vejle ----------/
        //                              (10)

        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }
        assertTrue(g.isConnected());
        g.removeEdge("vejle", "horsens");
        g.removeEdge("vejle", "give");

        assertTrue(g.containsVertice("vejle"));
        assertFalse(g.containsEdge("vejle", "horsens"));
        assertFalse(g.containsEdge("vejle", "give"));

        g.removeVertice("haderup");
        g.removeVertice("viborg");

        assertTrue(g.containsVertice("skive"));
        assertFalse(g.containsEdge("skive", "haderup"));
        assertFalse(g.containsEdge("skive", "viborg"));
    }

    @Test
    public void testDuplicates() {
        assertTrue(g.size() == 0);
        assertFalse(g.containsVertice("A"));
        assertFalse(g.containsVertice("B"));
        g.addEdge("A", "B", 8);
        assertTrue(g.size() == 2);
        assertTrue(g.containsVertice("A"));
        assertTrue(g.containsVertice("B"));
        g.addEdge("A", "B", 10);
        assertTrue(g.size() == 2);
        assertTrue(g.containsVertice("A"));
        assertTrue(g.containsVertice("B"));
        g.addEdge("B", "A", 3);
        assertTrue(g.size() == 2);
        assertTrue(g.containsVertice("A"));
        assertTrue(g.containsVertice("B"));
    }

    @Test
    public void testAddNull() {
        g.addEdge("A", null, 2);
        g.addEdge(null, "B", 3);
        assertTrue(g.containsVertice("A"));
        assertTrue(g.containsVertice("B"));
        assertFalse(g.containsEdge("A", "B"));
        g.addEdge("A", "B", 3);
        assertTrue(g.containsVertice("A"));
        assertTrue(g.containsVertice("B"));
        assertTrue(g.containsEdge("A", "B"));
        g.addEdge(null, null, 1);
        assertFalse(g.containsEdge(null, null));
    }

    @Test
    public void testAddVertice() {
        g.addVertice("A");
        g.addVertice("B");
        g.addVertice("C");
        g.addVertice("D");
        g.addVertice("E");
        g.addVertice("F");
        g.addVertice("A");
        assertTrue(g.containsVertice("A"));
        assertTrue(g.containsVertice("B"));
        assertTrue(g.containsVertice("C"));
        assertTrue(g.containsVertice("D"));
    }

    @Test
    public void testClear() {
        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }
        assertEquals(13, g.size());
        g.clear();
        assertEquals(0, g.size());
        g.addVertice("A");
        g.addVertice("B");
        assertEquals(2, g.size());
        g.clear();
        assertEquals(0, g.size());

    }


    @Test
    public void testNotConnected() {
        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }
        assertFalse(g.isConnected());

        //making the graph connected again
        g.addEdge("langaa", "aarhus", 20);
        assertTrue(g.isConnected());
    }

    @Test
    public void testForDuplicateEdges() {

        assertFalse(g.containsEdge("skanderborg", "aarhus"));
        assertTrue(g.numberOfEdges() == 0);

        g.addEdge("skanderborg", "aarhus", 16);
        assertTrue(g.containsEdge("skanderborg", "aarhus"));
        assertTrue(g.numberOfEdges() == 1);

        g.addEdge("skanderborg", "aarhus", 16);
        assertTrue(g.containsEdge("skanderborg", "aarhus"));
        assertTrue(g.numberOfEdges() == 1);
    }

    @Test
    public void testGraphCreation() {
        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }

        for (StartEndWeight sew : data) {
            assertTrue(g.containsEdge(sew.start, sew.end));
            assertTrue(g.containsEdge(sew.end, sew.start));
        }
        assertTrue(g.numberOfEdges() == data.size());

        // to test for duplicate data
        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }
        assertTrue(g.numberOfEdges() == data.size());
        for (StartEndWeight sew : data) {
            assertTrue(g.containsEdge(sew.start, sew.end));
            assertTrue(g.containsEdge(sew.end, sew.start));
            assertTrue(g.getWeight(sew.end, sew.start) == sew.weight);
            assertTrue(g.getWeight(sew.start, sew.end) == sew.weight);
        }
    }

    @Test
    public void testRemoveEdge(){
        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        g.removeEdge("skanderborg", "aarhus");

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }

        g.removeEdge("aarhus", "langaa");
        assertFalse(g.containsEdge("aarhus", "langaa"));
        assertTrue(g.containsVertice("aarhus"));
        assertTrue(g.containsVertice("langaa"));
        g.removeEdge("silkeborg", "kjellerup");
        assertFalse(g.containsEdge("aarhus", "langaa"));

        g.removeEdge("horsens", "vejle");
        assertFalse(g.containsEdge("aarhus", "langaa"));

        g.removeEdge("silkeborg", "skanderborg");
        assertFalse(g.containsEdge("aarhus", "langaa"));

        g.removeEdge("Hej", "hellO");
    }

    @Test
    public void testRemoveVertice() {
        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }

        assertTrue(g.containsVertice("silkeborg"));
        g.removeVertice("silkeborg");
        assertFalse(g.containsVertice("silkeborg"));
        assertFalse(g.containsEdge("silkeborg", "skanderborg"));
        assertFalse(g.containsEdge("silkeborg", "herning"));
        assertFalse(g.containsEdge("silkeborg", "kjellerup"));
        assertFalse(g.containsEdge("skanderborg", "silkeborg"));
        assertFalse(g.containsEdge("aarhus", "silkeborg"));

        assertTrue(g.containsEdge("haderup", "viborg"));
        assertTrue(g.containsEdge("aarhus", "langaa"));
        assertTrue(g.containsEdge("kjellerup", "viborg"));
        assertTrue(g.containsEdge("skive", "viborg"));

    }

    @Test
    public void testAddNegativeEdge() {
        assertEquals(0, g.size());
        g.addEdge("A", "B", 0);
        assertEquals(-1, g.getWeight("A", "B"), 0);
        assertEquals(0, g.size());
        g.addEdge("A", "B", 0.1);
        assertEquals(2, g.size());
        g.addEdge("C", "D", -0.1);
        assertEquals(2, g.size());
        g.addEdge("A", "B", 0);
        assertEquals(2, g.size());
        assertEquals(0.1, g.getWeight("A", "B"), 0);
    }

    @Test
    public void testGetWeight() {
        List<StartEndWeight> data = new LinkedList<>();

        data.add(new StartEndWeight("skanderborg", "aarhus", 16));
        data.add(new StartEndWeight("horsens", "skanderborg", 15));
        data.add(new StartEndWeight("aarhus", "langaa", 27));
        data.add(new StartEndWeight("aarhus", "silkeborg", 40));
        data.add(new StartEndWeight("silkeborg", "skanderborg", 30));
        data.add(new StartEndWeight("silkeborg", "herning", 35));
        data.add(new StartEndWeight("herning", "brande", 27));
        data.add(new StartEndWeight("brande", "give", 14));
        data.add(new StartEndWeight("give", "horsens", 15));
        data.add(new StartEndWeight("horsens", "vejle", 10));
        data.add(new StartEndWeight("give", "vejle", 20));
        data.add(new StartEndWeight("langaa", "viborg", 30));
        data.add(new StartEndWeight("kjellerup", "viborg", 20));
        data.add(new StartEndWeight("silkeborg", "kjellerup", 15));
        data.add(new StartEndWeight("herning", "haderup", 25));
        data.add(new StartEndWeight("haderup", "skive", 22));
        data.add(new StartEndWeight("haderup", "viborg", 25));
        data.add(new StartEndWeight("skive", "viborg", 20));

        for (StartEndWeight sew : data) {
            g.addEdge(sew.start, sew.end, sew.weight);
        }
        assertEquals(16, g.getWeight("skanderborg", "aarhus"), 0);
        assertEquals(15, g.getWeight("horsens", "skanderborg"), 0);
        assertEquals(27, g.getWeight("aarhus", "langaa"), 0);
        assertEquals(30, g.getWeight("silkeborg", "skanderborg"), 0);
        assertEquals(35, g.getWeight("silkeborg", "herning"), 0);
        assertEquals(20, g.getWeight("skive", "viborg"), 0);
        assertEquals(25, g.getWeight("haderup", "viborg"), 0);
        assertEquals(15, g.getWeight("silkeborg", "kjellerup"), 0);
        assertEquals(30, g.getWeight("langaa", "viborg"), 0);
    }
}