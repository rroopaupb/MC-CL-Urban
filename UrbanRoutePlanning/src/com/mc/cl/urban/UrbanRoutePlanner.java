package com.mc.cl.urban;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class UrbanRoutePlanner {
	final static Double INF = Double.MAX_VALUE;
	public LinkedList<Integer> finalpath = new LinkedList<>();
	public Double[][][] urbanMap;
	
	//int firstHouseNumber = 441;
	public int start = 441;
	public int target = 445;
	
	public Double travelTime = 0.0; // in seconds
	public boolean walkable = false;
	public boolean taxiSupported = true;
	public boolean pedestrianOnly = false;
	public Double carDistance = 0.0;
	
	public UrbanRoutePlanner(Double[][][] map, int start, int target) {
		super();
		this.urbanMap = map;
		this.start = start;
		this.target = target;
	}
	
	public UrbanRoutePlanner(int start, int target) {
		super();
		this.start = start;
		this.target = target;
	}

	public UrbanRoutePlanner() {
		
	}
	
	public boolean isWalkable() {
		return this.walkable;
	}
	
	public boolean isTaxiSupported() {
		return this.taxiSupported;
	}
	
	public boolean isPedestrianOnly() {
		return this.pedestrianOnly;
	}

	public Double[][] createGraph () throws FileNotFoundException, IOException {
		
		ExtractMap newmap = new ExtractMap();
		this.urbanMap = newmap.getMap();
		Double[][] graph = new Double[1700][1700]; 
		
		for (int i=0;i<1700;i++) {
        	for (int j=0;j<1700;j++) {
        			if (urbanMap[i][j][0] == 1.0) {
        				graph[i][j] = urbanMap[i][j][3];
        			}
        			else
        				graph[i][j] = INF;
        	}
		}	
		return graph;
	}
	
	public Double findShortestPath(Double[][] graph, int startNode, int endNode) {
		
		int startIdx = startNode - 1;
        int endIdx = endNode - 1;
        int length = graph.length;	
        Node[] allNodes = new Node[length];
		
        // startVisited record whether or not the node already visited by startNode and endNode
        boolean[] startVisited = new boolean[length];
        boolean[] endVisited = new boolean[length];
        int meet = 0;
        for (int i = 0; i < length; i++) {
        	allNodes[i] = new Node(i);
        	allNodes[i].startDistance = graph[startIdx][i];
            allNodes[i].endDistance = graph[i][endIdx];
            
        }
        startVisited[startIdx] = true;
        endVisited[endIdx] = true;
       
        Double startCurr = 0.0;
        Double endCurr = 0.0;
        Double shortestLength = allNodes[endIdx].startDistance;
        if (shortestLength < INF) {
        	return shortestLength;
        }
        
     // if next shortest distance for startNode plus next shortest distance for endNode is not smaller than current shortestLength, return it
        while (startCurr + endCurr < shortestLength) {
            // find next unvisited shortest distance for startNode
            int startNext = 0;
            Double startTmp = INF;
            for (int i = 0; i < length; i++) {
            	if (!startVisited[i] && allNodes[i].startDistance < startTmp) {
            		startTmp = allNodes[i].startDistance;
                    startNext = i;
                }
            }
            
            startCurr = startTmp;
            startVisited[startNext] = true;
            	            
            // find next unvisited shortest distance for endNode
            int endNext = 0;
            Double endTmp = INF;
            for (int i = 0; i < length; i++) {
                if (!endVisited[i] && allNodes[i].endDistance < endTmp) {
                	endTmp = allNodes[i].endDistance;
                    endNext = i;
                }
            }
            
            endCurr = endTmp;
            endVisited[endNext] = true;
            	            
            // if shortestLength is greater than startDistance[i] + endDistance[i] replace it
            for (int i = 0; i < length; i++) {
            	
                if (shortestLength > (allNodes[i].startDistance + allNodes[i].endDistance)) {
                	shortestLength = allNodes[i].startDistance + allNodes[i].endDistance;
                	meet = i;
                }
                
            }   
            // Update The startDistance Array, if the startDistance[i] is greater than startDistance[startNext] + graph[startNext][i]
            for (int i = 0; i < length; i++) {
                if (!startVisited[i] && (allNodes[i].startDistance > startTmp + graph[startNext][i])) {
                	allNodes[i].startDistance = startTmp + graph[startNext][i];
                	allNodes[i].shortestStartPath.addAll(allNodes[startNext].shortestStartPath);
                	allNodes[i].shortestStartPath.add(startNext);
                	
                }
                
             // Update The endDistance Array, if the endDistance[i] is greater than endDistance[endnext] + graph[endnext][i]
                
                if (!endVisited[i] && (allNodes[i].endDistance > endTmp + graph[endNext][i])) {
                	allNodes[i].endDistance = endTmp + graph[endNext][i];
                	allNodes[i].shortestEndPath.addAll(allNodes[endNext].shortestEndPath);
                	allNodes[i].shortestEndPath.add(endNext);
                	
                }
            }
        } 
        
        // Create final path
        this.finalpath.add(startIdx);
        this.finalpath.addAll(allNodes[meet].shortestStartPath);
        this.finalpath.add(meet);
        Iterator<Integer> iter = allNodes[meet].shortestEndPath.descendingIterator();
        while(iter.hasNext()) {
        	this.finalpath.add(iter.next());
        }
        this.finalpath.add(endIdx);
        System.out.println("Final Path : "+finalpath);
        
        if (shortestLength < 100)
        	walkable = true;
        calculateTime();
        return shortestLength;
		
	}
	
	public void calculateTime() {
		ListIterator list_Iter = finalpath.listIterator();
		while(list_Iter.hasNext()){ 
			int node1 = (int) list_Iter.next();
			int node2 = (int) list_Iter.next();
			Double distance = urbanMap[node1][node2][3];
			Double speed;
			if (isWalkable()) 
				speed = 1.7;
			else {
				speed = urbanMap[node1][node2][1];
				this.carDistance = this.carDistance + distance;
			}	
			Double time = distance/speed;
			if (urbanMap[node1][node2][4] == 0)
				this.taxiSupported = false;
			this.travelTime = this.travelTime + time;
			if (list_Iter.hasNext())
				list_Iter.previous();
			
		}	
		
	}
	
}
