package com.annamukhina.practice.core.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
* @author anna_mukhina
*/
@SlingServlet(paths = "/bin/test/events")
public class EventsServlet extends SlingAllMethodsServlet {
    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    private final String latitudeProperty = "latitude";

    private final String longitudeProperty = "longitude";

    private final String idProperty = "id";

    private final String rootNodename = "events";

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("EventsServlet doPost");

        String pathToPage = getPathToPage(request);

        String pathToRootNode = pathToPage + rootNodename;

        ResourceResolver resolver = request.getResourceResolver();

        Resource eventsResource = resolver.getResource(pathToRootNode);

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = reader.readLine();

        UUID uuid = UUID.randomUUID();

        ObjectMapper mapper = new ObjectMapper();

        Event event = mapper.readValue(json, Event.class);

        String nodeName = "e"+uuid;

        String id = uuid.toString();

        Map<String, Object> properties = new HashMap<>();

        properties.put(dateProperty,event.getDate());
        properties.put(placeProperty,event.getPlace());
        properties.put(cityProperty, event.getCity());
        properties.put(latitudeProperty, event.getLatitude());
        properties.put(longitudeProperty, event.getLongitude());
        properties.put(idProperty, id);

        ResourceResolver eventsResolver = eventsResource.getResourceResolver();

        eventsResolver.create(eventsResource, nodeName, properties);

        resolver.commit();

        PrintWriter out = response.getWriter();

        out.write("{}");

        out.flush();
    }

    @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("EventsServlet doPut");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String input = br.readLine();

        String[] inputArray = input.split(" ");

        String id = inputArray[0];

        String newDate = inputArray[1];

        String pathToPage = getPathToPage(request);

        String pathToRootNode = pathToPage + rootNodename + "/";

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
        LOGGER.debug("EventsServlet doDelete");

        String pathToPage = getPathToPage(request);

        String pathToRootNode = pathToPage + rootNodename;

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
        LOGGER.debug("EventsServlet doGet");

        String pathToPage = getPathToPage(request);

        String pathToRootNode = pathToPage + rootNodename;

        ResourceResolver resolver = request.getResourceResolver();

        Resource root = resolver.getResource(pathToRootNode);

        response.setContentType("application/json");

        try {
            Iterator<Resource> iterator = resolver.listChildren(root);

            List<String> eventsJSON = new ArrayList<>();

            while(iterator.hasNext()) {
                Resource nodeResource = iterator.next();

                ValueMap propertyMap  = nodeResource.getValueMap();

                ObjectMapper mapper = new ObjectMapper();

                String id = propertyMap.get(idProperty, String.class);

                String date = propertyMap.get(dateProperty, String.class);

                String place = propertyMap.get(placeProperty, String.class);

                String city = propertyMap.get(cityProperty, String.class);

                String latitude = propertyMap.get(latitudeProperty, String.class);

                String longitude = propertyMap.get(longitudeProperty, String.class);

                Event event = new Event(id, date, place, city, latitude, longitude);

                String eventJson = mapper.writeValueAsString(event);

                eventsJSON.add(eventJson);
            }
            PrintWriter out = response.getWriter();

            out.write(eventsJSON.toString());

            out.flush();
        } catch (IOException e) {
            LOGGER.error("Exception!", e);
        }
    }

    private String getPathToPage(SlingHttpServletRequest request) throws MalformedURLException {
        URL url = new URL(request.getHeader("referer"));
        String path = url.getPath();
        return path.replace(".html", "/");
    }
}
