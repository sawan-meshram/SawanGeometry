package com.sawan.test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonWriter;

import com.sawan.geometry.util.GeometryUtil;
import com.sawan.io.EsriJsonConstants;

public class GeoJSONTest {

	public enum EsriGeometryType{
		esriGeometryPoint ("x", "y", "spatialReference"),
		esriGeometryMultipoint ("points", "spatialReference"),
		esriGeometryPolyline ("paths", "spatialReference"),
		esriGeometryPolygon ("rings", "spatialReference"),
		esriGeometryEnvelope ("xmin", "ymin", "xmax", "ymax", "spatialReference");
		
		private final String[] contains;
		EsriGeometryType(String... contains){
			this.contains = contains;
		}
		public String[] getContainsKey() {
			return contains;
		}
	}
	
/*	public enum EsriSpatialReference{
		spatialReference ("spatialReference");
		
		private final String ref;
		EsriSpatialReference(String ref){
			this.ref = ref;
		}
		public String getSpatialReference() {
			return ref;
		}
	}*/
	
	private static boolean check(JSONObject obj, String...keys) {
		int count = 0;
		for(String key : keys) {
			if(obj.containsKey(key)) count++; //if key is found on json object
		}
		return keys.length == count ? true : false;
	}
	
	private static JSONParser parser = new JSONParser();

	private static ContainerFactory orderedKeyFactory = new ContainerFactory() {
		@Override
		public Map<String, Object> createObjectContainer() {
			return new LinkedHashMap<>();
		}
		
		@Override
		public List<Object> creatArrayContainer() {
			return new LinkedList<>();
		}
	};
	
	@SuppressWarnings("unchecked")
	private static LinkedHashMap<String, Object> getJsonObjectAsMap (String json) throws ParseException {
		return (LinkedHashMap<String, Object>) parser.parse(json, orderedKeyFactory);
	}
	
	@SuppressWarnings("unchecked")
	private static LinkedList<Object> getJsonObjectAsList (String json) throws ParseException {
		return (LinkedList<Object>) parser.parse(json, orderedKeyFactory);
	}
	
	public static void main(String[] args) throws ParseException, IOException {
		String wkt = "POLYGON ((-166.18194580078125 54.02713344412541, -165.673828125 54.02713344412541, -165.673828125 54.220284882124005, -166.18194580078125 54.220284882124005, -166.18194580078125 54.02713344412541))";
		Geometry g1 = GeometryUtil.toGeometry(wkt);
		GeoJsonWriter writer = new GeoJsonWriter();
		String gJson1 = writer.write(g1);
		System.out.println(gJson1);
		
/*		String json = "{\"points\":[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],\"spatialReference\":{\"wkid\":4326}}";
		LinkedHashMap map = getJsonObjectAsMap(json);
		System.out.println(map.size());
		System.out.println(json);
		
		map.forEach((k,v)->{
			System.out.println(k+" ::: "+v);
		});
*/		
		
//		JSONParser parser = new JSONParser();
		
		String json = "{\"rings\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832],[-97.06138,32.837]],[[-97.06326,32.759],[-97.06298,32.755],[-97.06153,32.749],[-97.06326,32.759]]],\"spatialReference\":{\"wkid\":4326}}";
		
//		boolean validate = validateEsriGeometryJson(json, EsriGeometryType.esriGeometryPolygon);
//		System.out.println(validate);
		
		json = "{\"hasZ\":true,\"hasM\":true,\"rings\":[[[-97.06138,32.837,35.1,4],[-97.06133,32.836,35.2,4.1],[-97.06124,32.834,35.3,4.2],[-97.06127,32.832,35.2,44.3],[-97.06138,32.837,35.1,4]],[[-97.06326,32.759,35.4],[-97.06298,32.755,35.5],[-97.06153,32.749,35.6],[-97.06326,32.759,35.4]]],\"spatialReference\":{\"wkid\":4326}}";
		
//		json = "{\"x\":-118.15,\"y\":33.80,\"spatialReference\":{\"wkid\":4326}}";
//		json = "{\"x\":null,\"spatialReference\":{\"wkid\":4326}}";
//		json = "{\"points\":[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],\"spatialReference\":{\"wkid\":4326}}";
//		json = "{\"paths\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],[[-97.06326,32.759],[-97.06298,32.755]]],\"spatialReference\":{\"wkid\":4326}}";
//		json = "{\"rings\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832],[-97.06138,32.837]],[[-97.06326,32.759],[-97.06298,32.755],[-97.06153,32.749],[-97.06326,32.759]]],\"spatialReference\":{\"wkid\":4326}}";
//		json = "{\"xmin\":-109.55,\"ymin\":25.76,\"xmax\":-86.39,\"ymax\":49.94,\"spatialReference\":{\"wkid\":4326}}";
		@SuppressWarnings("unchecked")
		Map<String, Object> geometryMap = (Map<String, Object>) parser.parse(new StringReader(json));
		validateEsriGeometryJson(geometryMap, EsriGeometryType.esriGeometryPolygon);
		
		EsriGeometryType type = findEsriGeometryType(geometryMap);
		System.out.println(type);
		
//		json= "{\"hasM\":true,\"paths\":[[[-97.06138,32.837,5],[-97.06133,32.836,6],[-97.06124,32.834,7],[-97.06127,32.832,8]],[[-97.06326,32.759],[-97.06298,32.755]]],\"spatialReference\":{\"wkid\":4326}}";
//		validate = validateEsriGeometryJson(json, EsriGeometryType.esriGeometryPolyline);
//		System.out.println(validate);
//		
//		String path = "{\"paths\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],[[-97.06326,32.759],[-97.06298,32.755]]],\"spatialReference\":{\"wkid\":4326}}";
//		validate = validateEsriGeometryJson(path, EsriGeometryType.esriGeometryPolyline);
//		System.out.println(validate);
//		
//		path = "{\"path\":[[[-97.06138,32.837],[-97.06133,32.836],[-97.06124,32.834],[-97.06127,32.832]],[[-97.06326,32.759],[-97.06298,32.755]]]}";
//		validate = validateEsriGeometryJson(path, EsriGeometryType.esriGeometryPolyline);
//		System.out.println(validate);
	}
	
	static boolean isFound(String supportKey, String jsonKey) {
		if(supportKey.equals(jsonKey)) return true;
		return false;
	}
	
	static boolean isType(EsriGeometryType type, Set<String> jsonKeys) {
		int count = 0;
		for(String jsonKey : jsonKeys) {
			for(String name : type.contains) {
				if(name.equals(EsriJsonConstants.NAME_SPATIAL_REFERENCE.getName()))continue;
				if(isFound(name, jsonKey))
					count++;
			}
		}
		if(count > 0) return true;
		return false;
	}
	
	static EsriGeometryType findEsriGeometryType(Map<String, Object> geometryMap) {
		
		Set<String> jsonKeys =  geometryMap.keySet();
		for(EsriGeometryType type : EsriGeometryType.values()) {
			if (isType(type, jsonKeys)) {
				return type;
			}	
		}
//		if (isType(EsriGeometryType.esriGeometryPoint, jsonKeys)) {
//			return EsriGeometryType.esriGeometryPoint;
//		} else if (isType(EsriGeometryType.esriGeometryMultipoint, jsonKeys)) {
//			return EsriGeometryType.esriGeometryMultipoint;
//		} else if (isType(EsriGeometryType.esriGeometryPolyline, jsonKeys)) {
//			return EsriGeometryType.esriGeometryPolyline;
//		} else if (isType(EsriGeometryType.esriGeometryPolygon, jsonKeys)) {
//			return EsriGeometryType.esriGeometryPolygon;
//		}else if (isType(EsriGeometryType.esriGeometryEnvelope, jsonKeys)) {
//			return EsriGeometryType.esriGeometryEnvelope;
//		}
		return null;
	}
	
	static void validateEsriGeometryJson(Map<String, Object> geometryMap, EsriGeometryType esriGeometryType) {

		Set<String> keySupportInJson = new HashSet<String>(Arrays.asList(esriGeometryType.getContainsKey()));
		
		Set<String> names =  geometryMap.keySet();
		
		StringBuilder sb = null;
		String delimiter = ", ";
		for(String name : names) {
			if(!keySupportInJson.contains(name)) {
				System.out.println("not spported :: "+name);
				
				if(sb == null) sb = new StringBuilder();
				else sb.append(delimiter);
				sb.append(name);
			}
		}
		if(sb!= null) {
			System.out.println("Sb ::"+sb.toString());
		}else {
			System.out.println("Success");
		}
	}
	
	
	static boolean validateEsriGeometryJson(String json, EsriGeometryType esriGeometryType) {
		System.out.println(json);
		try {
			LinkedHashMap<String, Object> map = getJsonObjectAsMap(json);
			String keys[] = esriGeometryType.getContainsKey();
			
			Set<String> keySet = map.keySet();
			boolean optional = keySet.contains("spatialReference") ? true : false ;

			int count = 0;
			for(String key : keys) {
				if(keySet.contains(key)) count++;
			}
			if(count == keySet.size() && optional) return true;
			if(count > 0 && (count == keySet.size()-1 || count == keySet.size()) && !optional) return true;
//			if(count > 0 && count == keySet.size() && !optional) return true;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

}
