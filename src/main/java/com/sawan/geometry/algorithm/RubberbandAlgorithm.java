/**
 * @author Sawan Meshram
 */
package com.sawan.geometry.algorithm;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import com.sawan.geometry.util.GeometryUtil;

public class RubberbandAlgorithm {

	/**
	 * This method is used to create Rubber band for a given WKT <i>(Well-Known Text)</i>.
	 * @param WKT is <i>(Well-Known Text)</i> of a geometry.
	 * @return a Rubberband {@code Geometry} object.
	 */
	public static Geometry createRubberband(String WKT){
		return createRubberband(GeometryUtil.toGeometry(WKT));
	}
	
	/**
	 * This method is used to create Rubber band for a given {@code Geometry}.
	 * @param geom is a input {@code Geometry}.
	 * @return a Rubberband {@code Geometry} object.
	 */
	public static Geometry createRubberband(Geometry geom){
		if(geom != null)
			return geom.convexHull();
		return null;
	}
	
	/**
	 * This method is used to create Rubber band for a given WKT <i>(Well-Known Text)</i>.
	 * @param WKT is <i>(Well-Known Text)</i> of a geometry.
	 * @return a Rubberband {@code MultiPolygon} object.
	 */
	public static MultiPolygon createRubberbandAsMultiPolygon(String WKT){
		return createRubberbandAsMultiPolygon(GeometryUtil.toGeometry(WKT));
	}
	
	/**
	 * This method is used to create Rubber band for a given {@code Geometry}.
	 * @param geom is a input {@code Geometry}.
	 * @return a Rubberband {@code MultiPolygon} object.
	 */
	public static MultiPolygon createRubberbandAsMultiPolygon(Geometry geom){
		Geometry convexHull = createRubberband(geom);
		
		if(convexHull != null && convexHull instanceof Polygon) {
			Polygon[] polys = new Polygon[1];
			polys[0] = (Polygon) convexHull;
			return GeometryUtil.getDefaultGeometryFactory().createMultiPolygon(polys);
		}
		return null;
	}
}
