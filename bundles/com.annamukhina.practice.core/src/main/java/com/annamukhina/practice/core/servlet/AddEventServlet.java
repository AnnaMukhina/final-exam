package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;

import javax.jcr.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.sling.commons.json.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/add")
public class AddEventServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events";

    private final String pathToServiceNode = "/apps/finalexam/components/tableComponent/service";

    private final String numberOfNodesProperty = "numberOfNodes";

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    private final String latitudeProperty = "latitude";

    private final String longitudeProperty = "longitude";

    private final String idProperty = "id";

    private final String rootNodeName = "event";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOGGER.debug("AddEventServlet doPost");

        ResourceResolver resolver = request.getResourceResolver();

        Resource eventsResource = resolver.getResource(pathToRootNode);

        Resource serviceResource = resolver.getResource(pathToServiceNode);

        Node root = eventsResource.adaptTo(Node.class);

        Node service = serviceResource.adaptTo(Node.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = reader.readLine();

        try {
            long numberOfNodes = service.getProperty(numberOfNodesProperty).getLong();

            numberOfNodes++;

            String nodeName = rootNodeName + numberOfNodes;

            service.setProperty(numberOfNodesProperty, numberOfNodes);

            Node event  = root.addNode(nodeName);

            JSONObject jsonObject = new JSONObject(json);

            String date = jsonObject.getString(dateProperty);

            String place = jsonObject.getString(placeProperty);

            String city = jsonObject.getString(cityProperty);

            String latitude = jsonObject.getString(latitudeProperty);

            String longitude = jsonObject.getString(longitudeProperty);

            event.setProperty(dateProperty,date );

            event.setProperty(placeProperty, place);

            event.setProperty(cityProperty, city);

            event.setProperty(latitudeProperty, latitude);

            event.setProperty(longitudeProperty, longitude);

            event.setProperty(idProperty, numberOfNodes);

            resolver.commit();

            PrintWriter out = response.getWriter();

            out.write("{}");

            out.flush();
        } catch (JSONException | RepositoryException e) {
            LOGGER.error("Exception!", e);
        }
    }
}
