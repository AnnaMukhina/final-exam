package com.annamukhina.practice.core.servlet;

import com.annamukhina.practice.core.bundle.Event;
import com.annamukhina.practice.core.bundle.Events;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.osgi.framework.Constants;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/events")
@Properties({@Property(name = Constants.SERVICE_DESCRIPTION, value = "Show Events Servlet"),
        @Property(name = Constants.SERVICE_VENDOR, value = "<Your Company>")})
public class ShowEventsServlet extends SlingAllMethodsServlet {
    Events events = new Events();

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        response.setContentType("text/html");
        List<Event> eventsList = events.getEvents();
        try {
            PrintWriter out = response.getWriter();
            for (Event event : eventsList) {
                out.write("<p>" + event.getDate() + " " + event.getPlace() + " " + event.getLocation() + "</p>");
            }
            out.flush();
        } catch (IOException e) {
        }
    }
}
