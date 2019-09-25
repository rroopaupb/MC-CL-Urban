package com.mc.cl.urban;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Plan {
	public static void main (String args[]) {
		int[] source = {441, 246}; 
		int[] destination = {444, 324};
		
		// Generate travels
		int travelCount = 2;
		Node[] srcNodes  ;
		for (int nod = 0; nod < travelCount; nod++) {
			//Node 
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
			Double distance = up.findShortestPath(graph,up.start,up.target);
			System.out.println("Distance : "+distance);
			System.out.println("Travel Time : "+up.travelTime);
			if (up.isWalkable()) {
				System.out.println("Walking route");
			}
		}
	}
}
