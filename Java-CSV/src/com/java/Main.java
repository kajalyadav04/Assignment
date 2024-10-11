package com.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String, String> hashMap = readCsvFile("read-data.csv");
		System.out.println(hashMap);
	}

	private static Map<String, String> readCsvFile(String fileName) {
		Map<String, String> hashMap = null;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			hashMap = new HashMap<>();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] strArrStrings = line.split(",");

				int n = strArrStrings.length;

				for (int i = 0; i < n; i++) {
					String[] eachStrings = strArrStrings[i].split(":");

					hashMap.put(eachStrings[0].trim(), eachStrings[1].trim());

				}

//				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashMap;

	}

}
