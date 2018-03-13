package adsassignment.collectionfactory;

import adsassignment.priorityqueue.IPriorityQueue;
import adsassignment.priorityqueue.linearPriorityqueue.LinearPQ;
import adsassignment.set.ISet;
import adsassignment.set.linearSet.LinearSet;


public class CollectionFactory {
     public enum SetType {
        LINEAR,
        BST,
        AVL,
        JAVA
    };

    public enum PQType {
        LINEAR,
        HEAP,
        JAVA
    };

    public static <T extends Comparable<T>> ISet<T> getSet(Class<T> c, SetType setType) {
        ISet<T> set = null;
        if(setType == SetType.LINEAR) {
            set = new LinearSet<>();
        } else if(setType == SetType.BST) {
            set = null;
        } else if(setType == SetType.AVL) {
            set = null;
        } else if(setType == SetType.JAVA) {
            set = new LinearSet<>();
        }
        return set;
    }

    public static <T> IPriorityQueue<T> getPQ(Class<T> c, PQType pqType) {
        IPriorityQueue<T> pq = null;
        if(pqType == PQType.LINEAR) {
            pq = new LinearPQ<>();
        } else if(pqType == PQType.HEAP) {
            pq = null;
        } else if(pqType == PQType.JAVA) {
            pq = new LinearPQ<>();
        }
        return pq;
    }
}
