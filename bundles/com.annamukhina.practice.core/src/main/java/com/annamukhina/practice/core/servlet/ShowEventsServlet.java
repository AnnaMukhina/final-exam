package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import java.io.*;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.*;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/events")
public class ShowEventsServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        LOGGER.debug("ShowEventsServlet doGet");

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(pathToRootNode);

        Node root = resource.adaptTo(Node.class);

        response.setContentType("application/json");

        try {
            NodeIterator iterator = root.getNodes();

            JSONArray eventsJSON = new JSONArray();

            while(iterator.hasNext()) {
                Node node = iterator.nextNode();

                JSONObject concertJSON = new JSONObject();

                String id = node.getProperty("id").getString();

                concertJSON.append("id", id);

                String date = node.getProperty("date").getString();

                concertJSON.append("date", date);

                String place = node.getProperty("place").getString();

                concertJSON.append("place", place);

                String city = node.getProperty("city").getString();

                concertJSON.append("city", city);

                String latitude = node.getProperty("latitude").getString();

                concertJSON.append("latitude", latitude);

                String longitude = node.getProperty("longitude").getString();

                concertJSON.append("longitude", longitude);

                eventsJSON.put(concertJSON);
            }
            PrintWriter out = response.getWriter();

            out.write(eventsJSON.toString());

            out.flush();
        } catch (RepositoryException | IOException | JSONException e) {
            LOGGER.error("Exception!", e);
        }
    }
}
