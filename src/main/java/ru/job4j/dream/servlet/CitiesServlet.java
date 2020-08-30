package ru.job4j.dream.servlet;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class CitiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        JSONArray array = new JSONArray();
        TreeMap<Integer, String> cities = new TreeMap<>(PsqlStore.instOf().citiesList());
        for (Integer id : cities.keySet()) {
            JSONObject city = new JSONObject(Map.of("id", id));
            city.put("name", cities.get(id));
            array.put(city);
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(array);
        writer.flush();
    }
}
