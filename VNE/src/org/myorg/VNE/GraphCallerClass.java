package org.myorg.VNE;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphCallerClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph graph1 = new Graph();
		createGraph1(graph1);
		
		//graph1.printGraph();
		//print
		
		Graph graph2 = new Graph();
		createGraph2(graph2);
		//graph2.printGraph();
		
		for(Node v: graph1.nodeSet) {
			System.out.println("Start Node " + v.getNodeName());
			
			System.out.println("Does it contain " + findIfGraphIsSubset(v, graph2.nodeSet.get(0)));
			
			System.out.println("");
		}
		
		
		//findIfGraphFits(graph1, graph2);
	}
	
	public static Graph createGraph1(Graph ourGraph) {
		Node v0 = new Node("0", 5, 5);
		Node v1 = new Node("1", 4, 4);
		Node v2 = new Node("2", 3, 3);
		Node v3 = new Node("3", 6, 6);
		Node v4 = new Node("4", 3, 3);
		Node v5 = new Node("5", 6, 6);
		
		ourGraph.addNodeToGraph(v0);
		ourGraph.addNodeToGraph(v1);
		ourGraph.addNodeToGraph(v2);
		ourGraph.addNodeToGraph(v3);
		ourGraph.addNodeToGraph(v4);
		ourGraph.addNodeToGraph(v5);
		
		//Add edge
		ourGraph.addEdge(v0, v1, 2);
        ourGraph.addEdge(v1, v2, 3);
        ourGraph.addEdge(v2, v3, 1);
        ourGraph.addEdge(v3, v4, 1);
        ourGraph.addEdge(v4, v5, 1);
        ourGraph.addEdge(v1, v5, 1);
		
		return ourGraph;
	}
	
	public static Graph createGraph2(Graph ourGraph) {
		Node v0 = new Node("0", 4, 4);
		Node v1= new Node("1", 3, 3);
		Node v2 = new Node("2", 2, 2);
		Node v3 = new Node("3", 4, 4);
		
		ourGraph.addNodeToGraph(v0);
		ourGraph.addNodeToGraph(v1);
		ourGraph.addNodeToGraph(v2);
		ourGraph.addNodeToGraph(v3);
		
		//Add edge
		ourGraph.addEdge(v0, v1, 1);
        ourGraph.addEdge(v1, v2, 2);
        ourGraph.addEdge(v2, v3, 0.9);
		
		return ourGraph;
	}
	
	public static boolean findIfGraphIsSubset(Node parent, Node child) {
		if(child == null) {
			System.out.println(" True????");
			return true;
		}
		if(parent == null) {
			return false;
		}
		if(ifParentNodeCanHoldChild(parent, child)) {
			System.out.println("Parent name" + parent.nodeName + " Chile name " + child.nodeName);
			for(Edge parentEdge: parent.edgeList ) {
				System.out.println("EdgeList??" + child + "Edge List??" + child.edgeList.size());
				if(child.edgeList.size() == 0) {
					return true;
				}
				Edge childEdge = child.edgeList.get(0);
				if(ifParentEdgeCanHoldChild(parentEdge, childEdge)) {
					System.out.println("Calling child " + childEdge.getDestVertex());
					return findIfGraphIsSubset(parentEdge.getDestVertex(), childEdge.getDestVertex());
				}
			}
		}
		return false;
	}
	
	public static boolean ifParentNodeCanHoldChild(Node parent, Node child) {
		System.out.println("ifParentNodeCanHoldChild: Parent name" + parent.nodeName + " Chile name " + child.nodeName);
		if(parent.cpuCapacity >= child.cpuCapacity && parent.memoryCapacity >= child.memoryCapacity) {
			return true;
		}
		return false;
	}
	
	public static boolean ifParentEdgeCanHoldChild(Edge parentEdge, Edge childEdge) {
		System.out.println("ifParentEdgeCanHoldChild: Parent name" + parentEdge.getWeight() + " Chile name " + childEdge.getWeight());
		if(parentEdge != null && childEdge != null && parentEdge.getWeight() >= childEdge.getWeight()) {
			return true;
		}
		return false;
	}
}

class Graph {
	LinkedList<Node> nodeSet = null;

	public LinkedList<Node> getNodeSet() {
		return nodeSet;
	}

	public Graph() {
		super();
		nodeSet = new LinkedList<>();
	}	
	
	public boolean addNodeToGraph(Node n) {
		return nodeSet.add(n);
	}
	
	public boolean addEdge(Node v1, Node v2, double weight) {
		//return v1.getEdges().add(new Edge(v2, weight)) && v2.getEdges().add(new Edge(v1, weight));

		return v1.getEdges().add(new Edge(v2, weight));
	}
	
	public void findSubnet(Node startNode) {
		HashSet<Node> visitedSet = new HashSet<>();
		Stack<Node> stack = new Stack<>();
		
		stack.push(startNode);
		
		while(!stack.isEmpty()) {
			Node temp = stack.pop();
			System.out.println(temp.nodeName);
			visitedSet.add(temp);
			
			for(Edge e : temp.edgeList) {
				if(!visitedSet.contains(e.getDestVertex())) {
					stack.push(e.getDestVertex());
					visitedSet.add(e.getDestVertex());
				}
			}
		}
	}
	
	public void printGraph() {
		HashSet<Node> visitedSet = new HashSet<>();
		Stack<Node> stack = new Stack<>();
		
		stack.push(nodeSet.peekFirst());
		
		while(!stack.isEmpty()) {
			Node temp = stack.pop();
			System.out.println(temp.nodeName);
			visitedSet.add(temp);
			
			for(Edge e : temp.edgeList) {
				if(!visitedSet.contains(e.getDestVertex())) {
					stack.push(e.getDestVertex());
					visitedSet.add(e.getDestVertex());
				}
				
			}
		}
		
//		HashSet<Node> visitedSet = new HashSet<>();
//		Queue<Node> queue = new ArrayDeque<Node>();
//		Node tempFirst  = nodeSet.peekFirst();
//		queue.add(tempFirst);
//		
//		while (!queue.isEmpty()) {
//			Node temp = queue.poll();
//			visitedSet.add(temp);
//
//			System.out.println("Graph element " + temp.nodeName);
//			for(Edge e : temp.edgeList) {
//				//System.out.println("in for  " + e.getDestVertex().nodeName);
//				if(!visitedSet.contains(e.getDestVertex())) {
//					queue.add(e.getDestVertex());
//				}
//			}
//			
//		}
		
//		for(Node v: nodeSet) {
//			System.out.println("Source Node name" + v.getNodeName());
//			for(Edge e: v.getEdges()) {
//				System.out.println("Dest Vertex Name" + e.getDestVertex().nodeName
//						+ ", weight " + e.getWeight());
//			}
//			System.out.println("\n");
//		}
	}
}

enum NodeType{
	TRANSPORT,
	PRESENTATION
}

class Node {
	String nodeName;
	List<Edge> edgeList;
	int cpuCapacity;
	int memoryCapacity;
	

	public Node(String nodeName, int cpuCapacity, int memoryCapacity) {
		super();
		this.nodeName = nodeName;
		this.edgeList = new LinkedList<>();
		this.cpuCapacity = cpuCapacity;
		this.memoryCapacity = memoryCapacity;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public List<Edge> getEdges() {
		return edgeList;
	}
	
	public void setEdges(List<Edge> edges) {
		this.edgeList = edges;
	}
	
}

class Edge {

	private double weight;
	private Node destVertex;
	
	public Edge(Node destVertex, double weight) {
		super();
		this.weight = weight;
		this.destVertex = destVertex;
	}

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Node getDestVertex() {
		return destVertex;
	}
	
	public void setDestVertex(Node destVertex) {
		this.destVertex = destVertex;
	}
}