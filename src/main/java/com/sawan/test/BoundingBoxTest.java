/**
 * @author Sawan Meshram
 */
package com.sawan.test;

import java.util.List;

import com.sawan.geometry.BoundingBox;
import com.sawan.geometry.algorithm.GriddedBoundingBox;
import com.sawan.geometry.util.BoundingBoxUtil;


public class BoundingBoxTest {
	
	public static void main(String[] args) {
		
		/*
		 * Demonstrate IsWithinBoundaryBox() using single test
		 */
		testIsWithinMultipleLatLng();

		/*
		 * Demonstrate IsWithinBoundaryBox() using multiple test
		 */
		testIsWithinSingleLatLng();
		

		/*
		 * Demonstrate griddedBoundingBox()
		 */
		griddedBoundingBoxUsingGrid_XY(); 
		
		
		/*
		 * Demonstrate griddedBoundingBox()
		 */
		griddedBoundingBoxUsingGrid_X_And_Y();
		
		/*
		 * Demonstrate createPolygon()
		 */
		testPolygonUsingBoundingBox();
		
		/*
		 * Demonstrate createMultiPolygon()
		 */
		testMultiPolygonUsingBoundingBox();
		
		/*
		 * Demonstrate intersect()
		 */
		testBoundingBoxIntersection();

	}
	
	public static void testBoundingBoxIntersection() {
		BoundingBox b1 = new BoundingBox(1, 1, 5, 5);
		BoundingBox b2 = new BoundingBox(1, 1, 0, 0);
		BoundingBox b3 = new BoundingBox(4, 2, 4, 4);
		
		System.out.println("Intersection :"+ BoundingBoxUtil.intersect(b1, b2));
		System.out.println("Intersection :"+ BoundingBoxUtil.intersect(b1, b3));

	}
	
	public static void testPolygonUsingBoundingBox() {
		BoundingBox b1 = new BoundingBox(1, 1, 5, 5);
		System.out.println(b1.createPolygon());
	}
	
	public static void testMultiPolygonUsingBoundingBox() {
		BoundingBox b1 = new BoundingBox(1, 1, 5, 5);
		System.out.println(b1.createMultiPolygon());
	}

	public static void griddedBoundingBoxUsingGrid_X_And_Y() {
		BoundingBox b1 = new BoundingBox(1, 1, 5, 5);

		List<BoundingBox> griddedBBox = GriddedBoundingBox.griddedBoundingBox(b1, 2, 3);
		for(BoundingBox bbox : griddedBBox) {
			System.out.println(bbox.createPolygon());
		}
	}
	
	public static void griddedBoundingBoxUsingGrid_XY() {
		BoundingBox b1 = new BoundingBox(1, 1, 5, 5);

		List<BoundingBox> griddedBBox = GriddedBoundingBox.griddedBoundingBox(b1, 2);
		for(BoundingBox bbox : griddedBBox) {
			System.out.println(bbox.createPolygon());
		}
	}
	
	public static void testIsWithinMultipleLatLng() {

		double[][] coordinates = {
			{41.325004, -124.024739},
			{41.319676, -124.060080},
			{41.374617, -123.995557},
			{41.369593, -124.006200},
			{41.249417, -124.124732},
			{41.251418, -124.121041},
			{41.253547, -124.095635}
		};
		
		for(double[] points : coordinates){
			boolean check = BoundingBoxUtil.IsWithinBoundaryBox(-124.124999999865, 41.2499999991692, -124.000000000306, 41.3749999988581, points[0], points[1]);
			checkIfFailed(check, "Lat-lng is not within bounding box");
		}
	}
	
	public static void testIsWithinSingleLatLng() {
		
		double lat = 41.319676;
		double lng = -124.060080;
		
 		boolean check = BoundingBoxUtil.IsWithinBoundaryBox(-124.124999999865, 41.2499999991692, -124.000000000306, 41.3749999988581, lat , lng);
 		checkIfFailed(check, "Lat-lng is not within bounding box");
	}
	
	public static void checkIfFailed(boolean flag, String msg) {
		if(!flag) {
			System.out.println(msg);
		}
	}
}
