package com.annamukhina.practice.core.servlet;

import com.annamukhina.practice.core.bundle.Events;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.io.*;

import org.osgi.framework.Constants;
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

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path);

        Node events = resource.adaptTo(Node.class);

        response.setContentType("text/html");

        try {
            PrintWriter out = response.getWriter();

            NodeIterator iterator = events.getNodes();

            while(iterator.hasNext()) {
                Node event = iterator.nextNode();
                Property date = event.getProperty("date");
                out.write("<p>" + date.getString() + " ");
                Property place = event.getProperty("place");
                out.write(place.getString() + " ");
                Property location = event.getProperty("location");
                out.write(location.getString() + "</p>");
                }
            } catch (PathNotFoundException e2) {
            e2.printStackTrace();
        } catch (RepositoryException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
