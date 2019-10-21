package com.mc.cl.urban;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Plan {
	public static void main (String args[]) {
		int[] source = {441, 246}; 
		int[] destination = {444, 324};
		
		//Create Nodes
		Node[] nodes = new Node[1700] ;
		for (int i = 0; i < 1700; i++) {
			nodes[i] = new Node(i);
		}
		
		// Generate travels
		int travelCount = 2;
		
		//Update Nodes
		for (int trvl = 0; trvl < travelCount; trvl++) {
			nodes[source[trvl]].updateTravels(destination[trvl]);
		}
		
		UrbanRoutePlanner gr = new UrbanRoutePlanner(441,442);
		Double[][] graph = {{0.0}};
		try {
			graph = gr.createGraph();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int tr = 0; tr < travelCount ; tr++) {
			UrbanRoutePlanner up = new UrbanRoutePlanner(gr.urbanMap,source[tr],destination[tr]);
			//nodes[source[tr]].updateTravels(destination[tr]);
			Double distance = up.findShortestPath(graph,up.start,up.target);
			System.out.println("Distance : "+distance);
			System.out.println("Travel Time : "+up.travelTime);
			if (up.isWalkable()) {
				System.out.println("Walking route");
			}
			nodes[source[tr]].setTravelDetails(destination[tr], up.finalpath ,distance, up.travelTime );
		}
	}
}
