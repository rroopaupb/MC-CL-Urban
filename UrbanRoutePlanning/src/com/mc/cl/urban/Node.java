package com.mc.cl.urban;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.random.AbstractRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;

public class Node {
	public int NodeId;
	public LinkedList<Integer> shortestStartPath = new LinkedList<>();
	public LinkedList<Integer> shortestEndPath = new LinkedList<>();
	public Double distance = Double.MAX_VALUE;
	public Double startDistance = Double.MAX_VALUE;
	public Double endDistance = Double.MAX_VALUE;
	public int noOfPeople = 0;
	public int noOfCars = 0;
	// Constructors
	public Node() {
		this.NodeId = 0;
	}
	
	public Node(int nodeid) {
		this.NodeId = nodeid;
		this.distance = 0.0;
		generatePopulation();
		generateCars();
	}
	
	public Node(int nodeid,Double shortestdistance) {
		this.NodeId = nodeid;
		this.distance = shortestdistance;
	}
	
	//Getters and setters
	public int getNodeId() {
		return NodeId;
	}

	public void setNodeId(int nodeId) {
		NodeId = nodeId;
	}

	public List<Integer> getShortestStartPath() {
		return shortestStartPath;
	}


	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public void printNode() {
		System.out.println("Node Id : "+this.NodeId+ " , Distance : "+ this.distance+" , shortesstpath : "+this.shortestStartPath);
		
	}

	public List<Integer> getShortestEndPath() {
		return shortestEndPath;
	}


	public void generatePopulation() {
		Random rpeople = new Random();
		int people = (int)(rpeople.nextGaussian()*10);
		while (people > 5 || people <= 0) {
			people = (int)(rpeople.nextGaussian()*10);
		}
		this.noOfPeople = people;
	}
	
	public void generateCars() {
		AbstractRandomGenerator rGen ;
		ExponentialDistribution rCars = new ExponentialDistribution(0.05);
		int cars = 0;
		while (cars > this.noOfPeople || cars <= 0) {
			//cars = (int)(rCars.cumulativeProbability(0.02)*100%4);
			//System.out.println(cars);
			cars = new Random().nextInt(4)+1;
		}
		this.noOfCars = cars;
		//System.out.println("Node : "+this.NodeId+" People : "+this.noOfPeople+" Cars : "+this.noOfCars);
	}

	//public boolean isCarSharingRequired
}

