/**
 * @author Sawan Meshram
 */
package com.sawan.geometry;

import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.algorithm.hull.ConcaveHull;
import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.shape.random.RandomPointsBuilder;
import org.locationtech.jts.shape.random.RandomPointsInGridBuilder;

public class GenerateRandomPolygon {
	public enum GenerateType{
		Convex_Hull,
		Concave_Hull
	}
	
	/**
	 * This method is used to generate the random points inside a given {@code Polygon} geometry.
	 * @param g is {@code Polygon} Geometry
	 * @param numOfPoints user to decide how many random points
	 * @return {@code MultiPoint} geometry
	 */
	public static Geometry generateRandomMultiPointInPolygon(Geometry polygon, int numOfPoints) {
		RandomPointsBuilder builder = new RandomPointsBuilder();
		builder.setExtent(polygon);
		builder.setNumPoints(numOfPoints);
		return builder.getGeometry();
	}

	/**
	 * This method is used to generate the random points inside a given {@code Envelope}.
	 * @param envelope is {@code Envelope}.
	 * @param numOfPoints user to decide how many random points
	 * @return {@code MultiPoint} geometry
	 */
	public static Geometry generateRandomMultiPointInEnvelope(Envelope envelope, int numOfPoints) {	
		RandomPointsInGridBuilder builder = new RandomPointsInGridBuilder();
		builder.setExtent(envelope);
		builder.setNumPoints(numOfPoints);
		builder.setConstrainedToCircle(true);
		return builder.getGeometry(); 
	}
	
	
	/**
	 * This method is used to generate the Polygon using {@code MultiPoint} geometry
	 * @param multiPoint is {@code MultiPoint} Geometry
	 * @return {@code Polygon} geometry
	 */
	public static Geometry buildRandomMultiPointToPolygon(Geometry multiPoint, GenerateType type) {
		
		if(type == GenerateType.Concave_Hull) {
			ConcaveHull hull = new ConcaveHull(multiPoint);
			try {
				return ((ConcaveHull) hull).getHull();
			}catch (NullPointerException e) {
				System.out.println("Concave hull is not able to build :"+e);
			}
		}
		else if(type == GenerateType.Convex_Hull) {
			ConvexHull hull = new ConvexHull(multiPoint); //aka Rubber band
			
			try {
				return ((ConvexHull) hull).getConvexHull();
			}catch (NullPointerException e) {
				System.out.println("Convex hull is not able to build :"+e);
			}
		}
		return null;
/*		ConcaveHull hull = new ConcaveHull(multiPoint);
		try {
			return hull.getHull();
		}catch (NullPointerException e) {
			System.out.println("Concave hull is not able to build :"+e);
		}
		return null;
*/	}

}
