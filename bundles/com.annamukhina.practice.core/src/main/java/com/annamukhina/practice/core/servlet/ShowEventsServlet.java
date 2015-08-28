package com.annamukhina.practice.core.servlet;

import com.annamukhina.practice.core.bundle.Events;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
    private final String path = "/apps/finalexam/components/tableComponent/events.xml/jcr:content";
    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    Events events = new Events();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path);

        Node node = resource.adaptTo(Node.class);

            try {
                Property jcrData = node.getProperty("jcr:data");
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.write(jcrData.getString());
                out.flush();

            } catch (RepositoryException e) {
                e.printStackTrace();
            } catch (IOException e) {
                LOGGER.error("Exception!", e);
            }

        //Node node = resource.adaptTo(Node.class);

//        List<Event> eventsList = events.getEvents();
//        List toJson = new ArrayList();
//        for(Event event : eventsList) {
//            String date = event.getDate();
//            String place = event.getPlace();
//            String location = event.getLocation();
//            toJson.add(date);
//            toJson.add(place);
//            toJson.add(location);
//        }
//        response.setContentType("application/json");
//        String jsonText = JSONValue.toJSONString(toJson);

    }
}
