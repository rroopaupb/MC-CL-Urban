package com.mc.cl.urban;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Plan {
	public static void main (String args[]) {
		UrbanRoutePlanner up = new UrbanRoutePlanner();
		try {
			Double[][] graph = up.createGraph();
			Double distance = up.findShortestPath(graph,146,182);
			System.out.println("Shortest distance : "+distance);
						
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
