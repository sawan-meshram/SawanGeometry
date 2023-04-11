/**
 * @author Sawan Meshram
 */
package com.sawan.geometry.util;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import com.sawan.geometry.GenerateRandomPolygon;
import com.sawan.geometry.GenerateRandomPolygon.GenerateType;
import com.sawan.geometry.algorithm.DiamondationAlgorithm;
import com.sawan.geometry.algorithm.DiamondationAlgorithm.GeometryType;

public class RandomPolygonUtil {
	
	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * By <i>{@code default}</i> the <b>{@code GenerateType}</b> will be <b><i>{@code Concave_Hull}</b></i> for build random polygon. 
	 * <br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param WKT is input WKT <i>(Well-Known Text)</i> of {@code Geometry} i.e. {@code Polygon}
	 * @param gridXY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong> & <strong>axis-X</strong>.
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(String WKT, int gridXY, int numOfPoints) {
		return generateRandomPolygons(WKT, gridXY, numOfPoints, GenerateType.Concave_Hull);
	}

	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * By <i>{@code default}</i> the <b>{@code GenerateType}</b> will be <b><i>{@code Concave_Hull}</b></i> for build random polygon. 
	 * <br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param geom is input {@code Geometry} i.e. {@code Polygon}
	 * @param gridXY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong> & <strong>axis-X</strong>.
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(Geometry geom, int gridXY, int numOfPoints) {
		return generateRandomPolygons(geom, gridXY, numOfPoints, GenerateType.Concave_Hull);
	}

	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param WKT is input WKT <i>(Well-Known Text)</i> of {@code Geometry} i.e. {@code Polygon}
	 * @param gridXY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong> & <strong>axis-X</strong>.
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @param type is {@code GenerateType} to build {@code Polygon} using random {@code MultiPoint}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(String WKT, int gridXY, int numOfPoints, GenerateType type) {
		return generateRandomPolygons(WKT, gridXY, gridXY, numOfPoints, type);
	}
	
	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param geom is input {@code Geometry} i.e. {@code Polygon}
	 * @param gridXY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong> & <strong>axis-X</strong>.
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @param type is {@code GenerateType} to build {@code Polygon} using random {@code MultiPoint}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(Geometry geom, int gridXY, int numOfPoints, GenerateType type) {
		return generateRandomPolygons(geom, gridXY, gridXY, numOfPoints, type);
	}
	
	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * By <i>{@code default}</i> the <b>{@code GenerateType}</b> will be <b><i>{@code Concave_Hull}</b></i> for build random polygon. 
	 * <br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param WKT is input WKT <i>(Well-Known Text)</i> of {@code Geometry} i.e. {@code Polygon}
	 * @param gridX is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong>
	 * @param gridY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-Y</strong>
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(String WKT, int gridX, int gridY, int numOfPoints) {
		return generateRandomPolygons(WKT, gridX, gridY, numOfPoints, GenerateType.Concave_Hull);
	}
	
	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * By <i>{@code default}</i> the <b>{@code GenerateType}</b> will be <b><i>{@code Concave_Hull}</b></i> for build random polygon. 
	 * <br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param geom is input {@code Geometry} i.e. {@code Polygon}
	 * @param gridX is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong>
	 * @param gridY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-Y</strong>
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(Geometry geom, int gridX, int gridY, int numOfPoints) {
		return generateRandomPolygons(geom, gridX, gridY, numOfPoints, GenerateType.Concave_Hull);
	}
	
	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param WKT is input WKT <i>(Well-Known Text)</i> of {@code Geometry} i.e. {@code Polygon}
	 * @param gridX is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong>
	 * @param gridY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-Y</strong>
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @param type is {@code GenerateType} to build {@code Polygon} using random {@code MultiPoint}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(String WKT, int gridX, int gridY, int numOfPoints, GenerateType type) {
		return generateRandomPolygons(GeometryUtil.toGeometry(WKT), gridX, gridY, numOfPoints, type);
	}
	
	/**
	 * This method is used to generate random number of polygons within input {@code Geometry} i.e. {@code Polygon}.<br>
	 * <p><i><b>Notes : </b>Used <b>{@code DiamondationAlgorithm}</b> to splitted input {@code Geometry}. 
	 * And there is a chance that user specified or required random Polygons will not get while using algorithm. 
	 * Random Polygon count may less get as per user requirement.</i></p>
	 * @param geom is input {@code Geometry} i.e. {@code Polygon}
	 * @param gridX is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-X</strong>
	 * @param gridY is input number for <i>spliting</i> the input {@code Geometry} on <strong>axis-Y</strong>
	 * @param numOfPoints is input number for how many random {@code MultiPoint} to build individual random {@code Polygon}.
	 * @param type is {@code GenerateType} to build {@code Polygon} using random {@code MultiPoint}.
	 * @return a {@code List} collection that contains random {@code Geometry} i.e. {@code Polygon} or {@code MultiPolygon}.
	 */
	public static List<Geometry> generateRandomPolygons(Geometry geom, int gridX, int gridY, int numOfPoints, GenerateType type) {
		List<Geometry> list = new ArrayList<>();
		/*
		 * Split the geometry into gridXY parts
		 */
		List<Geometry> geometries = DiamondationAlgorithm.splitOriginalGeometry(geom, gridX, gridY, GeometryType.MultiPolygon);
		
		for(Geometry g : geometries) {
			
			/*
			 * If splitted geometry is not either MultiPolygon or Polygon, then skip this geometry
			 */
			if(!(g instanceof MultiPolygon || g instanceof Polygon)) continue;
			/*
			 * Finding the random points in splitted Polygon
			 */
			Geometry randomPoints = GenerateRandomPolygon.generateRandomMultiPointInPolygon(g, numOfPoints);
			/*
			 * Build polygon using random points
			 */
			Geometry randomPolygon = GenerateRandomPolygon.buildRandomMultiPointToPolygon(randomPoints, type);
			if(randomPolygon == null)continue;
			
			if(randomPolygon instanceof Polygon || randomPolygon instanceof MultiPolygon) {
				list.add(randomPolygon);
			}//eof IF
		}
		return list;
	}
}
