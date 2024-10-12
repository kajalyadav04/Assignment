package com.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
//		Read data from a CSV file and store it in a HashMap
		Map<String, String> csvFileData = readCsvFile("read-data.csv");
		System.out.println(csvFileData);
		
        // Evaluate the cell values based on the data in csvFileData
		Map<String, Integer> valueMap = evaluateCell(csvFileData);
		System.out.println(valueMap);
		
        // Write the evaluated data to a new CSV file
		writeData("write-data.csv", valueMap);
	}
	
	private static Map<String, String> readCsvFile(String fileName) {
		Map<String, String> csvFileData = null;
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			csvFileData = new LinkedHashMap<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] strArrStrings = line.split(",");
				int n = strArrStrings.length;
				for (int i = 0; i < n; i++) {
					String[] eachStrings = strArrStrings[i].split(":");
					csvFileData.put(eachStrings[0].trim(), eachStrings[1].trim());
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

		return csvFileData;

	}

    // Function to evaluate cell values from the input CSV data
	private static Map<String, Integer> evaluateCell(Map<String, String> csvFileData) {
		// TODO Auto-generated method stub
		Map<String, Integer> originalValueMap = new LinkedHashMap<>();
		for (Entry<String, String> entry : csvFileData.entrySet()) {
			String valueEntry = entry.getValue();
			String getKeyString = entry.getKey();

            // If the value starts with "=", it's a formula, calculate it
			if (valueEntry.startsWith("=")) {
				String valueExp = valueEntry.substring(1);
				int calculated = calculateFormula(valueExp, originalValueMap, csvFileData);
				originalValueMap.put(getKeyString, calculated);
			} else {
				originalValueMap.put(getKeyString, Integer.parseInt(valueEntry));
			}
		}
		return originalValueMap;
	}
	
    // Function to calculate a formula based on cell references and values
	private static int calculateFormula(String valueExp, Map<String, Integer> originalValueMap,
			Map<String, String> csvFileData) {
		String[] stringsArrRhs = valueExp.split("\\+");
		int add = 0;
		for (String ch : stringsArrRhs) {
			if (csvFileData.containsKey(ch)) {
				if (originalValueMap.containsKey(ch)) {
					add += originalValueMap.get(ch);
				}
			} else {
				add += Integer.parseInt(ch);
			}
		}

		return add;

	}

    // Function to write the calculated data to a CSV file
	private static void writeData(String fileWriter, Map<String, Integer> valueMap) {
		try (BufferedWriter writeData = new BufferedWriter(new FileWriter(fileWriter))) {

            // Loop through the valueMap and write each entry (key: value) to the file
			for (Entry<String, Integer> entry : valueMap.entrySet()) {
				writeData.append(entry.getKey())
					.append(": ")
					.append(String.valueOf(entry.getValue()))
					.append(", ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
