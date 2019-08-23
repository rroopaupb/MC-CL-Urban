package com.mc.cl.urban;

import java.util.HashSet;
import java.util.Set;


public class BidirectionalDijsktra {
	final static Double INF = Double.MAX_VALUE; //INF means the node is not connect
	Set<Integer> path = new HashSet<>();    
	
	static int meetNode = 0; // This is the node that two directions meet at

	    /**
	     *
	     * @param graph
	     * @param start
	     * @param end
	     * @return the shortest length between start node and end node
	     */
	    public Double BiDijkstra(Double[][] graph, int startNode, int endNode) {
	        int length = graph.length;
	        int startIdx = startNode - 1;
	        int endIdx = endNode - 1;
	        	    	
	        // startDistance[i] means the distance between startNode and i node, same for endDistance array
	        Double[] startDistance = new Double[length];
	        Double[] endDistance = new Double[length];

	        // startVisited record whether or not the node already visited by startNode and endNode
	        boolean[] startVisited = new boolean[length];
	        boolean[] endVisited = new boolean[length];
	        
	        boolean meetingNode = false;

	        for (int i = 0; i < length; i++) {
	            startDistance[i] = graph[startIdx][i];
	            endDistance[i] = graph[endIdx][i];
	        }
	        startVisited[startIdx] = true;
	        endVisited[endIdx] = true;
	        Double startCurr = 0.0;
	        Double endCurr = 0.0;
	        Double shortestLength = startDistance[endIdx];
	        if (shortestLength < INF) {
	        	return shortestLength;
	        }
	        	
		        // if next shortest distance for startNode plus next shortest distance for endNode is not smaller than current shortestLength, return it
		        while (startCurr + endCurr < shortestLength) {
		            // find next unvisited shortest distance for startNode
		            int startNext = 0;
		            Double startTmp = INF;
		            for (int i = 0; i < length; i++) {
		            	if (!startVisited[i] && startDistance[i] < startTmp) {
		            		startTmp = startDistance[i];
		                    startNext = i;
		                    System.out.println("Finding startNext : "+i);
		                }
		            }
		            System.out.println("Start next : "+startNext);
                    
		            startCurr = startTmp;
		            startVisited[startNext] = true;
		            	            
		            // find next unvisited shortest distance for endNode
		            int endNext = 0;
		            Double endTmp = INF;
		            for (int i = 0; i < length; i++) {
		                if (!endVisited[i] && endDistance[i] < endTmp) {
		                	endTmp = endDistance[i];
		                    endNext = i;
		                    System.out.println("Finding endNext : "+i);
		                }
		            }
		            System.out.println("End Next : "+endNext);
                    
		            endCurr = endTmp;
		            endVisited[endNext] = true;
		            	            
		            // if shortestLength is greater than startDistance[i] + endDistance[i] replace it
		            for (int i = 0; i < length; i++) {
		            	
		                if (shortestLength > (startDistance[i] + endDistance[i])) {
		                	System.out.println("shortest length before update at node "+i+" to "+shortestLength);
		                	System.out.println("StartDistance : "+startDistance[i]);
		                	System.out.println("endDistance : "+endDistance[i]);
		                	shortestLength = startDistance[i] + endDistance[i];
		                	meetNode = i + 1;
		                	System.out.println("Update shortest length at node "+i+" to "+shortestLength);
		                	path.add(i);
		                }
		                
		            }   
		             /*// Update The startDistance Array, if the startDistance[i] is greater than startDistance[startNext] + graph[startNext][i]
			            
		                if (!startVisited[i] && (startDistance[i] > startTmp + graph[startNext][i])) {
		                	System.out.println("Update path start to "+i+" through "+ startNext);
		                	startDistance[i] = startTmp + graph[startNext][i];
		                }
		             // Update The endDistance Array, if the endDistance[i] is greater than endDistance[endnext] + graph[endnext][i]
				          
		                if (!endVisited[i] && (endDistance[i] > endTmp + graph[i][endNext])) {
		                	System.out.println("Update path end to "+i+" through "+ endNext);
		                	endDistance[i] = endTmp + graph[i][endNext];
		                	
		                }
		            }*/
	
		           /* // Update The startDistance Array, if the startDistance[i] is greater than startDistance[startNext] + graph[startNext][i]
		            for (int i = 0; i < length; i++) {
		                if (!startVisited[i] && (startDistance[i] > startTmp + graph[startNext][i])) {
		                	System.out.println("Update path start to "+i+" through "+ startNext);
		                	startDistance[i] = startTmp + graph[startNext][i];
		                }
		            }
	
		            // Update The endDistance Array, if the endDistance[i] is greater than endDistance[endnext] + graph[endnext][i]
		            for (int i = 0; i < length; i++) {
		                if (!endVisited[i] && (endDistance[i] > endTmp + graph[endNext][i])) {
		                	System.out.println("Update path end to "+i+" through "+ endNext);
		                	endDistance[i] = endTmp + graph[endNext][i];
		                }
		            }*/
		            
		        }   
	        path.add(endIdx);    
	        System.out.println("Path : "+path);
	        return shortestLength;
	    }

	    /*public static void main(String[] args) {
	        // Test Case
	        Double[][] graph = {
	            {0.0, INF, 40.0, 120.0, INF, 95.0, 129.0, 140.0, INF, INF, INF},
	            {INF, 0.0, 103.0, INF, INF, INF, INF, INF, 72.0, INF, INF},
	            {40.0, 103.0, 0.0, 104.0, INF, 90.0, INF, 89.0, INF, INF, 198.0},
	            {120.0, INF, 104.0, 0.0, INF, INF, INF, INF, INF, INF, INF},
	            {INF, INF, INF, INF, 0.0, 97.0, INF, INF, 112.0, INF, INF},
	            {95.0, INF, 90.0, INF, 97.0, 0.0, INF, 146.0, INF, 200.0, INF},
	            {129.0, INF, INF, INF, INF, INF, 0.0, INF, INF, 40.0, INF},
	            {140.0, INF, 89.0, INF, INF, 146.0, INF, 0.0, INF, INF, INF},
	            {INF, 72.0, INF, INF, 112.0, INF, INF, INF, 0.0, INF, INF},
	            {INF, INF, INF, INF, INF, 200.0, 40.0, INF, INF, 0.0, INF},
	            {INF, INF, 198.0, INF, INF, INF, INF, INF, INF, INF, 0.0}
	        };
	        int startNode = 7;
	        int endNode = 9;
	        BidirectionalDijsktra test = new BidirectionalDijsktra();
	        Double path = test.BiDijkstra(graph, startNode, endNode);
	        System.out.println("The meeting node is node " + meetNode + ". ");
	        System.out.println("The shortest path from " + startNode + " to " + endNode + " is " + path + ".");
	    }*/
	
}
