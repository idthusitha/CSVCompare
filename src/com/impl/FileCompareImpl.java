package com.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FileCompareImpl {

	public JSONArray uploadFile(ServletFileUpload uploader, HttpServletRequest request, HttpServletResponse response) throws ServletException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new ServletException("Content type is not multipart/form-data");
		}

		HashMap<String, TreeMap<String, String>> allFileMap = new HashMap<String, TreeMap<String, String>>();

		try {
			int fileCount = 0;
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while (fileItemsIterator.hasNext()) {
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName=" + fileItem.getFieldName());
				System.out.println("FileName=" + fileItem.getName());
				System.out.println("ContentType=" + fileItem.getContentType());
				System.out.println("Size in bytes=" + fileItem.getSize());

				File file = new File(request.getSession().getServletContext().getAttribute("FILES_DIR") + File.separator + fileItem.getName());
				System.out.println("Absolute Path at server=" + file.getAbsolutePath());
				fileItem.write(file);

				TreeMap<String, String> map = readCSV(file);
				allFileMap.put("FILE_COUNT_" + fileCount, map);
				fileCount++;

			}
		} catch (FileUploadException e) {
			System.out.println("Exception in uploading file.");
		} catch (Exception e) {
			System.out.println("Exception in uploading file.");
		}

		System.out.println("allFileMap.size()==>" + allFileMap.size());
		System.out.println("FILE_COUNT_0==>" + allFileMap.get("FILE_COUNT_0").size());
		System.out.println("FILE_COUNT_1==>" + allFileMap.get("FILE_COUNT_1").size());

		TreeMap<String, String> map0 = allFileMap.get("FILE_COUNT_0");
		TreeMap<String, String> map1 = allFileMap.get("FILE_COUNT_1");

		JSONArray array = new JSONArray();

		JSONObject json = new JSONObject();
		json.accumulate("HEADING", map0.get("HEADING"));

		json.accumulate("CSV01_CSV02", compareData(map0, map1));

		json.accumulate("CSV02_CSV01", compareData(map1, map0));

		array.add(json);
		return array;

	}

	private JSONArray compareData(TreeMap<String, String> map0, TreeMap<String, String> map1) {
		JSONArray jsonArray = new JSONArray();

		JSONObject json = null;

		/***************************************/
		ArrayList<String> list1 = new ArrayList<String>(map1.values());
		for (String key : map0.keySet()) {
			String rowKey = map0.get(key);

			if (key.startsWith("ROW_")) {
				if (list1.contains(rowKey)) {
					list1.remove(rowKey);
				}
			} else {
				list1.remove(rowKey);
			}
		}
		System.out.println("this data not in list 1==>" + list1.size());

		for (String key : list1) {
			json = new JSONObject();
			if (!"".equals(key)) {
				json.accumulate("ROW_DATA", key);
				jsonArray.add(json);
			}
		}

		/***************************************/
		return jsonArray;

	}

	public TreeMap<String, String> readCSV(File file) throws Exception {

		TreeMap<String, String> map = new TreeMap<String, String>();

		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		int rowCount = 0;
		for (String line : lines) {
			ArrayList<String> rowStringList = new ArrayList<String>();

			/******************************************************/
			String letterTemp = "";
			for (String letter : line.split("")) {

				if (",".equals(letter) && !letterTemp.startsWith("\"")) {
					rowStringList.add(letterTemp);
					letterTemp = "";

				} else if (letterTemp.length() != 1 && letterTemp.startsWith("\"") && letterTemp.endsWith("\"")) {
					rowStringList.add(letterTemp);
					letterTemp = "";
				}
				letterTemp += !",".equals(letter) ? letter : letterTemp.startsWith("\"") ? letter : "";
			}
			rowStringList.add(letterTemp);
			System.out.println("Row Column Count==>" + rowStringList.size());
			/******************************************************/

			String rowKey = "";
			for (int i = 0; i < rowStringList.size(); i++) {
				rowKey += rowStringList.get(i) + (i < rowStringList.size() - 1 ? "@@@@" : "");
			}
			if (rowCount == 0) {
				map.put("HEADING", rowKey);
				System.out.println("Heading Coloumn Count==>" + map.get("HEADING").toString());
			} else {
				map.put("ROW_" + (rowCount < 10 ? "0" : "") + rowCount, rowKey);
			}

			System.out.println("row added " + rowCount);
			rowCount++;
		}
		return map;
	}
}
