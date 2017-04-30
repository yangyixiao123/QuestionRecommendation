package com.sicnu.yudidi.service;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sicnu.yudidi.utils.file.FileEx;

public class RecommendationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = getServletContext();
		String path = sc.getRealPath("/WEB-INF/classes/com/sicnu/yudidi/service/arrays.txt");
		String json = FileEx.readByReader(new File(path));
		Writer writer = resp.getWriter();
		writer.write(json);
	}

	private Map<String, List<String>> name() {
		Map<String, List<String>> map = new HashMap<>();
		ServletContext sc = getServletContext();
		String path = sc.getRealPath("/WEB-INF/classes/com/sicnu/yudidi/service/7-clusters.txt");
		String[] txt = FileEx.readLineByReader(new File(path));

		for (String line : txt) {
			String[] items = line.split(",");
			String clusterId = items[0];
			for (int i = 2; i < items.length; i++) {
				if (map.get(clusterId) == null) {
					List<String> subjectsList = new ArrayList<>();
					subjectsList.add(items[i]);
					map.put(clusterId, subjectsList);
				} else {
					map.get(clusterId).add(items[i]);
				}
			}
		}
		return map;
	}

	
}
