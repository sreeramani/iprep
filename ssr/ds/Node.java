package com.ssr.ds;

import java.util.Objects;

public class Node {
    private String name;
    private float distanceFromRoot = Integer.MAX_VALUE;
    private boolean visited = false;
    private Node prevNode;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getDistanceFromRoot() {
        return distanceFromRoot;
    }

    public void setDistanceFromRoot(float distanceFromRoot) {
        this.distanceFromRoot = distanceFromRoot;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(Node prevNode) {
        this.prevNode = prevNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return (this.getName() == ((Node) o).getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
