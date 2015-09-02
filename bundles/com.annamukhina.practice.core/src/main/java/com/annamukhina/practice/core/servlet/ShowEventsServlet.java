package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import java.io.*;
import java.util.Iterator;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author anna_mukhina
*/
@SlingServlet(paths = "/bin/test/events")
public class ShowEventsServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    private final String latitudeProperty = "latitude";

    private final String longitudeProperty = "longitude";

    private final String idProperty = "id";


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
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
