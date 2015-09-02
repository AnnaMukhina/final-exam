package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.sling.commons.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/add")
public class AddEventServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events";

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    private final String latitudeProperty = "latitude";

    private final String longitudeProperty = "longitude";

    private final String idProperty = "id";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
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
}
