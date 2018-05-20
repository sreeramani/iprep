package com.ssr.ds;

public class Edge {
    private Node srcNode;
    private Node destNode;
    private  float weight;

    public Edge(Node srcNode, Node destNode, float weight) {
        this.srcNode = srcNode;
        this.destNode = destNode;
        this.weight = weight;
    }

    public Node getSrcNode() {
        return srcNode;
    }

    public Node getDestNode() {
        return destNode;
    }

    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return (srcNode.getName() +" -> " + destNode.getName() +" : " +weight);
    }
}
