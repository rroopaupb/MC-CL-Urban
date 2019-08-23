package com.mc.cl.urban;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jmatio.io.MatFileReader;
import com.jmatio.types.MLDouble;

public class ExtractMap {
	
	
	public Double[][][] getMap() throws FileNotFoundException, IOException {
		int ndims = 3;
		int[] dims = new int[]{2, 3, 4, 5, 6};
		
		String fileName = "src/resources/A1.mat";
        MatFileReader reader = new MatFileReader(fileName);
        MLDouble mlArray = (MLDouble) reader.getMLArray("ans");

		Double expectedVal = 0.0;
		Double[][][] actual = new Double[1700][1700][5];
		
		for (int i=0;i<1700;i++) {
        	for (int j=0;j<1700;j++) {
        		for (int k=0;k<5;k++) {
        			actual[i][j][k] = mlArray.get(mlArray.getIndex(i, j, k));
        			//System.out.println("Map("+i+", "+j+", "+k+") : "+actual[i][j][k]);
        		}
        	}
        }
		
		/*for (int i=441;i<446;i++) {
        	for (int j=441;j<446;j++) {
        		for (int k=0;k<5;k++) {
        			//actual[i][j][k] = mlArray.get(mlArray.getIndex(i, j, k));
        			System.out.println("Map("+i+", "+j+", "+k+") : "+actual[i][j][k]);
        		}
        	}
        }*/
		return actual;
	}
	
	
}
