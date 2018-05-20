package com.ssr.ds;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphAdjList {
    private Map<Node, ArrayList<Edge>> nodeArrayListMap;
    private int numVertices;
    private int numEdges;
    private boolean isDirected = false;
    private Node rootNode;

    GraphAdjList(boolean isDirected){
        numVertices = 0;
        numEdges = 0;
        nodeArrayListMap = new HashMap<Node, ArrayList<Edge>>();
        this.isDirected = isDirected;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
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

    public void addNode(Node node) {
        ArrayList<Edge> edges = new ArrayList<>();
        nodeArrayListMap.put(node, edges);
        numVertices++;
    }

    public void addEdge(Node source, Node destination, float distance)
    {
        if(nodeArrayListMap.containsKey(source))
        {
            Edge edge = new Edge(source, destination, distance);
            nodeArrayListMap.get(source).add(edge);
            if(!isDirected()) {
                nodeArrayListMap.get(destination).add(edge);
            }
            numEdges++;
        }
        else
        {
            System.out.println("Source vertex not found..");
        }
    }

    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for(Edge edge : nodeArrayListMap.get(node))
        {
            neighbors.add(edge.getDestNode());
        }
        return neighbors;
    }

    public void print() {
        for (Map.Entry<Node,ArrayList<Edge>> entry : nodeArrayListMap.entrySet()) {
            for(Edge edge: entry.getValue()) {
                System.out.println(edge.toString());
            }
        }
    }

    float getDistance(Node source, Node destination)
    {
        for(Edge edge : nodeArrayListMap.get(source))
        {
            if(edge.getDestNode() == destination)
            {
                return edge.getWeight();
            }
        }
        return 0;
    }

    //Dijkstra’s shortest path
    public void getDijkstraMinDistance() {
        Set<Node> nodes = nodeArrayListMap.keySet();
        Node nextNode = getRootNode();

        for (int i = 0; i < nodes.size(); i++) {
            for (Node neighbor : getNeighbors(nextNode)) {
                float distance = nextNode.getDistanceFromRoot() + getDistance(nextNode, neighbor);
                if (distance < neighbor.getDistanceFromRoot()) {
                    neighbor.setDistanceFromRoot(distance);
                    neighbor.setPrevNode(nextNode);
                }
            }
            nextNode.setVisited(true);
            nextNode = getShortestDistanceUnvisitedNode(nodes);
        }
        System.out.println("Node -> Prev : Distance");
        for (Node node: nodes) {
            System.out.println(node.getName() + " -> " + node.getPrevNode().getName() + " : " + node.getDistanceFromRoot());
        }
    }

    private Node getShortestDistanceUnvisitedNode(Set<Node> nodes) {
        float distance = Integer.MAX_VALUE;
        Node shortestDist = null;
        for (Node node: nodes) {
            if(!node.isVisited() && node.getDistanceFromRoot() < distance) {
                distance = node.getDistanceFromRoot();
                shortestDist = node;
            }
        }
        return shortestDist;
    }

    public static void main(String[] args) {
        GraphAdjList graph = new GraphAdjList(true);
        Node nodeA = new Node("A");
        nodeA.setDistanceFromRoot(0);
        nodeA.setPrevNode(nodeA);
        graph.setRootNode(nodeA);
        graph.addNode(nodeA);

        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");

        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeG);

        graph.addEdge(nodeA, nodeB, 2);
        graph.addEdge(nodeA, nodeC, 5);
        graph.addEdge(nodeB, nodeC, 2);
        graph.addEdge(nodeB, nodeD, 2);
        graph.addEdge(nodeC, nodeE, 4);
        graph.addEdge(nodeD, nodeE, 2);
        graph.addEdge(nodeC, nodeF, 2);
        graph.addEdge(nodeD, nodeG, 2);
        graph.addEdge(nodeF, nodeG, 3);
        graph.addEdge(nodeE, nodeF, 5);

        //System.out.println("Graph");
        //graph.print();
        System.out.println("Distance from root node");
        graph.getDijkstraMinDistance();
    }

}
