/**
 * @author Sawan Meshram
 */
package com.sawan.geometry.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.sawan.geometry.BoundingBox;

public class GriddedBoundingBox {
	
	/**
	 * This method is used to find out the gridded bounding box of a given {@code BoundingBox}.
	 * @param b is a input {@code BoundingBox}.
	 * @param gridX is value of grid on x-axis.
	 * @param gridY is value of grid on y-axis.
	 * @return {@code List} collection of {@code BoundingBox}.
	 * 
	 */
	public static List<BoundingBox> griddedBoundingBox(BoundingBox b, int gridX, int gridY){
//		System.out.println("BoundingBox Grid for 'X' = '"+ gridX + "' & 'Y' = '"+gridY +"'");
		double grid_x = ((b.getXmax() - b.getXmin()) / gridX);
		double grid_y = ((b.getYmax() - b.getYmin()) / gridY);
		
		List<BoundingBox> list = new ArrayList<>();

		for(int i = 0; i < gridX; i++){
			for(int j = 0; j < gridY; j++){
				
				double _xmin = b.getXmin() + (i * grid_x);
				double _ymin = b.getYmin() + (j * grid_y);
				
				double _xmax = _xmin + grid_x;
				double _ymax = _ymin + grid_y;
				
				list.add(new BoundingBox(_xmin, _ymin, _xmax, _ymax));
			}
		}
//		System.out.println("Total grid form = "+list.size());
		return list;
	}
	
	/**
	 * This method is used to find out the gridded bounding box of a given {@code BoundingBox}.
	 * @param b is a input {@code BoundingBox}.
	 * @param grid is value of grid on x-axis & y-axis.
	 * @return {@code List} collection of {@code BoundingBox}.
	 */
	public static List<BoundingBox> griddedBoundingBox(BoundingBox b, int grid){
		return griddedBoundingBox(b, grid, grid);
	}
}
