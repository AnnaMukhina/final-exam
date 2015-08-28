package com.annamukhina.practice.core.servlet;

import com.annamukhina.practice.core.bundle.Concert;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.sling.commons.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.*;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/events")
//@Properties({@Property(name = Constants.SERVICE_DESCRIPTION, value = "Show Events Servlet"),
//        @Property(name = Constants.SERVICE_VENDOR, value = "<Your Company>")})
public class ShowEventsServlet extends SlingAllMethodsServlet {
    private final String path = "/apps/finalexam/components/tableComponent/events";
    private List<String> dates = new ArrayList<>();
    private List<String> places = new ArrayList<>();
    private List<String> locations = new ArrayList<>();

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path);

        Node events = resource.adaptTo(Node.class);

        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();

            NodeIterator iterator = events.getNodes();

            while(iterator.hasNext()) {
                Node event = iterator.nextNode();

                String date = event.getProperty("date").getString();

                dates.add(date);

                String place = event.getProperty("place").getString();

                places.add(place);

                String location = event.getProperty("location").getString();

                locations.add(location);
            }

            JSONArray jsonDates = new JSONArray(Arrays.asList(dates));
            JSONArray jsonPlaces = new JSONArray(Arrays.asList(places));
            JSONArray jsonLocations = new JSONArray(Arrays.asList(locations));

            out.write(jsonDates.toString());
            out.write(jsonPlaces.toString());
            out.write(jsonLocations.toString());
        } catch (PathNotFoundException e2) {
            e2.printStackTrace();
        } catch (RepositoryException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
