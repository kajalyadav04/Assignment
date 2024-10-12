package com.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		Map<String, String> hashMapCsvData = readCsvFile("read-data.csv");
		evaluateCell(hashMapCsvData);
		Map<String, Integer> valueMap = evaluateCell(hashMapCsvData);
		System.out.println(hashMapCsvData);
		System.out.println(valueMap);
	}
	
	

	

	private static Map<String, Integer> evaluateCell(Map<String, String> hashMapCsvData) {
		// TODO Auto-generated method stub
		Map<String, Integer> originalValueMap=new HashMap<>();
		for(Entry<String, String> entry: hashMapCsvData.entrySet()) {
			String valueEntry = entry.getValue();
			String getKeyString=entry.getKey();
			
			if(valueEntry.startsWith("=")) {
				String valueExp=valueEntry.substring(1);
				int calculated=calculateFormula(valueExp,originalValueMap,hashMapCsvData);
				originalValueMap.put(getKeyString, calculated);
			}
			else {
				 originalValueMap.put(getKeyString,Integer.parseInt(valueEntry));
			}
		}
		return originalValueMap;
	}

	private static int calculateFormula(String valueExp,Map<String,Integer>originalValueMap,Map<String,String> hashMapCsvData) {
		   String[] stringsArrRhs =valueExp.split("\\+");
//		   System.out.println(Arrays.toString(stringsArrRhs));
		   int add=0;
		   for(String ch:stringsArrRhs) {
			   if(hashMapCsvData.containsKey(ch)) {
				   if(originalValueMap.containsKey(ch)) {
					   add+=originalValueMap.get(ch);
				   }
				   
			   }else {
				   add+=Integer.parseInt(ch);
			   }
		   }
				
		return add;
		
	}

	private static Map<String, String> readCsvFile(String fileName) {
		Map<String, String> hashMapCsvData = null;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			hashMapCsvData = new HashMap<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] strArrStrings = line.split(",");
				int n = strArrStrings.length;
				for (int i = 0; i < n; i++) {
					String[] eachStrings = strArrStrings[i].split(":");
					hashMapCsvData.put(eachStrings[0].trim(), eachStrings[1].trim());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return hashMapCsvData;

	}

}
