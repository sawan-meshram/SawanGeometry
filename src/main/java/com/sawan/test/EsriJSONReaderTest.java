package com.sawan.test;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;

import com.sawan.io.EsriGeometryUtil;
import com.sawan.io.EsriJsonReader;

public class EsriJSONReaderTest {
	public static void main(String[] args) throws ParseException {
		EsriJsonReader reader = new EsriJsonReader();
		
		//pass
//		String point = "{\"x\":-103.20762634277341,\"y\":35.04658137962862,\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(point);
		
		//pass
//		String multipoint = "{\"points\":[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(multipoint);

		//pass
//		String multilinestring = "{\"paths\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],[[-97.06326,32.759],[-97.06298,32.755]]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(multilinestring);

		//pass
//		String linestring = "{\"paths\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(linestring);
		
		//failed, it shld be Multipolygon
//		String polygon = "{\"rings\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832],[-97.06138,32.837]],[[-97.06326,32.759],[-97.06298,32.755],[-97.06153,32.749],[-97.06326,32.759]]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(polygon);

		//pass
//		String multipolygon = "{\"rings\":[[[-97.11502075195312,32.7783266418036],[-97.12120056152345,32.76764572990649],[-97.10197448730469,32.76389267278154],[-97.09270477294922,32.76966654128219],[-97.10472106933594,32.77688335023984],[-97.11502075195312,32.7783266418036]],[[-97.11227416992188,32.77313068261677],[-97.11570739746094,32.76793442005467],[-97.1026611328125,32.76620226512162],[-97.09785461425781,32.76764572990649],[-97.11227416992188,32.77313068261677]],[[-97.14111328124999,32.78207909030684],[-97.12051391601561,32.78005856077817],[-97.12635040283203,32.770821270041424],[-97.13939666748045,32.773708026397244],[-97.14111328124999,32.78207909030684]]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(multipolygon);
		
		//pass -> single polygon
//		String polygon = "{\"rings\":[[[-97.13141441345215,32.77385236175715],[-97.12450504302979,32.77569261707643],[-97.11291790008544,32.7747905358906],[-97.11502075195312,32.76916134273928],[-97.13141441345215,32.77385236175715]]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(polygon);
	
		//pass
//		String polygonWithHoles = "{\"rings\":[[[-97.13141441345215,32.77385236175715],[-97.12450504302979,32.77569261707643],[-97.11291790008544,32.7747905358906],[-97.11502075195312,32.76916134273928],[-97.13141441345215,32.77385236175715]],[[-97.12358236312866,32.77457403504532],[-97.12469816207887,32.773509564895136],[-97.1170163154602,32.77228270214884],[-97.11450576782227,32.7731487246666],[-97.12358236312866,32.77457403504532]]],\"spatialReference\":{\"wkid\":4326}}";
//		Geometry g = reader.read(polygonWithHoles);
		
		//pass
		String multipolygonWithHolesAndOtherPoly = "{\"rings\":[[[-97.13141441345215,32.77385236175715],[-97.12450504302979,32.77569261707643],[-97.11291790008544,32.7747905358906],[-97.11502075195312,32.76916134273928],[-97.13141441345215,32.77385236175715]],[[-97.12358236312866,32.77457403504532],[-97.12469816207887,32.773509564895136],[-97.1170163154602,32.77228270214884],[-97.11450576782227,32.7731487246666],[-97.12358236312866,32.77457403504532]],[[-97.11066484451294,32.77343739696647],[-97.10875511169435,32.774213199132774],[-97.10819721221924,32.77201206838387],[-97.10909843444824,32.76964849852645],[-97.10989236831665,32.770460418913416],[-97.11066484451294,32.77343739696647]]],\"spatialReference\":{\"wkid\":4326}}";
		Geometry g = reader.read(multipolygonWithHolesAndOtherPoly);

		System.out.println(g);
		
		Polygon p1 = (Polygon)g;
		
		Geometry g1 = EsriGeometryUtil.convertPolygonOrMultiPolygon(p1);
		System.out.println(g1);
		
//		System.out.println(((Polygon)g).getNumGeometries());
//		GeometryFactory factory = new GeometryFactory();
//		System.out.println(factory.createPolygon(p1.getExteriorRing()));
//		Polygon p11 = factory.createPolygon(p1.getExteriorRing());
//		
//		List<Polygon> list = new ArrayList<>();
//		List<LinearRing> innerRings = new ArrayList<>();
//		for(int i = 0; i < p1.getNumInteriorRing(); i++) {
//			Polygon p111 = factory.createPolygon(p1.getInteriorRingN(i));
//			if(p11.contains(p111)) {
//				innerRings.add(p1.getInteriorRingN(i));
//			}
//			else
//				list.add(p111);
//		}
//		
//		if(innerRings.size() > 0) {
//			Polygon p2 = factory.createPolygon(p11.getExteriorRing(), innerRings.toArray(new LinearRing[innerRings.size()]));
//			list.add(p2);
//		}
//		if(list.size()> 1) {
//			System.out.println(factory.createMultiPolygon(list.toArray(new Polygon[list.size()])));
//		}

/*		Collections.sort(list, new Comparator<Geometry>() {

			@Override
			public int compare(Geometry o1, Geometry o2){
				return Double.compare(o1.getArea(), o2.getArea());
			}
		});

		
*/
//		System.out.println(GeometryUtil.buildGeometryCollection(list).union());
		
	}
}
