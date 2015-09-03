package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
* @author anna_mukhina
*/
@SlingServlet(paths = "/bin/test/events")
public class EventsServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/content/finalexam/events/";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    private final String latitudeProperty = "latitude";

    private final String longitudeProperty = "longitude";

    private final String idProperty = "id";

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("AddEventServlet doPost");

        ResourceResolver resolver = request.getResourceResolver();

        Resource eventsResource = resolver.getResource(pathToRootNode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = reader.readLine();

        try {
            ResourceResolver eventsResolver = eventsResource.getResourceResolver();

            UUID uuid = UUID.randomUUID();

            JSONObject jsonObject = new JSONObject(json);

            String nodeName = "e"+uuid;

            String date = jsonObject.getString(dateProperty);

            String place = jsonObject.getString(placeProperty);

            String city = jsonObject.getString(cityProperty);

            String latitude = jsonObject.getString(latitudeProperty);

            String longitude = jsonObject.getString(longitudeProperty);

            String id = uuid.toString();

            Map<String, Object> properties = new HashMap<>();

            properties.put(dateProperty, date);
            properties.put(placeProperty, place);
            properties.put(cityProperty, city);
            properties.put(latitudeProperty, latitude);
            properties.put(longitudeProperty, longitude);
            properties.put(idProperty, id);

            eventsResolver.create(eventsResource, nodeName, properties);

            resolver.commit();

            PrintWriter out = response.getWriter();

            out.write("{}");

            out.flush();
        } catch (JSONException e) {
            LOGGER.error("Exception!", e);
        }
    }

    @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("EditEventServlet doPost");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String input = br.readLine();

        String[] inputArray = input.split(" ");

        String id = inputArray[0];

        String newDate = inputArray[1];

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(pathToRootNode + "e" + id);

        ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);

        properties.put(dateProperty, newDate);

        resolver.commit();

        PrintWriter out = response.getWriter();

        out.write("{}");

        out.flush();
    }

    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("DeleteEventServlet doPost");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String id = br.readLine();

        String nodeName = "e" + id;

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(pathToRootNode);

        Resource child = resource.getChild(nodeName);

        resolver.delete(child);

        resolver.commit();

        PrintWriter out = response.getWriter();

        out.write("{}");

        out.flush();
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("ShowEventsServlet doGet");

        ResourceResolver resolver = request.getResourceResolver();

        Resource root = resolver.getResource(pathToRootNode);

        response.setContentType("application/json");

        try {
            Iterator<Resource> iterator = resolver.listChildren(root);

            JSONArray eventsJSON = new JSONArray();

            while(iterator.hasNext()) {
                Resource nodeResource = iterator.next();

                ValueMap propertyMap  = nodeResource.getValueMap();

                JSONObject concertJSON = new JSONObject();

                String id = propertyMap.get(idProperty, String.class);

                concertJSON.append(idProperty, id);

                String date = propertyMap.get(dateProperty, String.class);

                concertJSON.append(dateProperty, date);

                String place = propertyMap.get(placeProperty, String.class);

                concertJSON.append(placeProperty, place);

                String city = propertyMap.get(cityProperty, String.class);

                concertJSON.append(cityProperty, city);

                String latitude = propertyMap.get(latitudeProperty, String.class);

                concertJSON.append(latitudeProperty, latitude);

                String longitude = propertyMap.get(longitudeProperty, String.class);

                concertJSON.append(longitudeProperty, longitude);

                eventsJSON.put(concertJSON);
            }
            PrintWriter out = response.getWriter();

            out.write(eventsJSON.toString());

            out.flush();
        } catch (IOException | JSONException e) {
            LOGGER.error("Exception!", e);
        }
    }
}
