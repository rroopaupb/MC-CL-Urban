package com.mc.cl.urban;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	public int totalNoOfCars = 0;
	public int carsAvailable = 0;
	public LinkedList<Integer> travels = new LinkedList<>();
	public HashMap<Integer,LinkedList<Integer>> travelRoutes = new HashMap<Integer, LinkedList<Integer>> ();
	public HashMap<Integer,Double> travelDistances = new HashMap<Integer, Double>();
	public HashMap<Integer,Double> travelTime = new HashMap<Integer, Double>();
	public Double maxVal = -1.0;
	public int max = -1;
	boolean carSharing = false;
	
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
		int cars = -1;
		while (cars > this.noOfPeople || cars < 0 ) {
			//cars = (int)(rCars.cumulativeProbability(0.02)*100%4);
			//System.out.println(cars);
			cars = new Random().nextInt(3);
		}
		this.totalNoOfCars = cars;
		this.carsAvailable = cars;
		
		//System.out.println("Node : "+this.NodeId+" People : "+this.noOfPeople+" Cars : "+this.noOfCars);
	}
	
	public void updateTravels(int dest) {
		this.travels.add(dest);
		System.out.println("Updated node "+ this.NodeId);
	}
	//public boolean isCarSharingRequired
	
	public void carSharing() {
		int travelCnt = this.travels.size();
		if (this.totalNoOfCars == 0 || this.carsAvailable == 0) 
			return;
		int farthest = max(this.travelTime);
		switch (travelCnt) {
		case 1 : break;
		case 2 : switch (carsAvailable) {
				 case 1 : Double t1 = travelTime.get(farthest);
				 		  Double t2 = 0.0;
				 		  for (Integer k : travels) {
				 			  if (k != farthest )
				 				  t2 = travelTime.get(k);
				 		  }
				 		  Double t3 = 0.0;
				 		  
				 		  break;
				 }
		
		}
	}
	
	public int max(HashMap<Integer,Double> times) {
		//Double[] value = times.values;
		times.forEach((k,v) -> {
			if (v > maxVal) {
				maxVal = v;
				max =k;
			}	
		});
		return max;
	}
	
	public void setTravelRoutes(int dest, LinkedList<Integer> path) {
		this.travelRoutes.put(dest,new LinkedList<> (path));
	}
	
	public void setTravelDistance(int dest, Double distance) {
		this.travelDistances.put(dest,distance);
	}
	
	public void setTravelTime(int dest, Double time) {
		this.travelTime.put(dest,time);
	}
	
	public void setTravelDetails(int dest, LinkedList<Integer> path, Double distance, Double time) {
		setTravelRoutes(dest, path);
		setTravelDistance(dest, distance);
		setTravelTime(dest, time);
	}
}

