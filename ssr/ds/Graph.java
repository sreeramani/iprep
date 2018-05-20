package com.ssr.ds;

public abstract class Graph {
    private int numVertices;
    private int numEdges;
    private boolean isDirected = false;

    public Graph()
    {
        this.numVertices = 0;
        this.numEdges = 0;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
    }

    public void addVertex(Node node) {
        implementAddVertex(node);
        numVertices++;
    }

    public void addEdge(Node srcNode, Node destNode, float distance) {
        implementAddEdge(srcNode, destNode, distance);
        numEdges++;
    }

    protected abstract void implementAddVertex(Node node);
    protected abstract void implementAddEdge(Node srcNode, Node destNode, float distance);
}
