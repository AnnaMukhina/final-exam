package com.annamukhina.practice.core.servlet;

import com.day.cq.commons.PathInfo;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/find")
public class FindEventServlet extends SlingAllMethodsServlet {
    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    private final String rootNodename = "events";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOGGER.debug("FindEventServlet doPost");

        String pathToPage = getPathToPage(request);

        String pathToRootNode = pathToPage + rootNodename;

        List<String> eventList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String template =  br.readLine();

        ResourceResolver resolver = request.getResourceResolver();

        Resource rootResource = resolver.getResource(pathToRootNode);

        PrintWriter out = response.getWriter();

        Iterator<Resource> iterator = resolver.listChildren(rootResource);

        while(iterator.hasNext()) {
            Resource nodeResource = iterator.next();

            ValueMap propertyMap  = nodeResource.getValueMap();

            String city = propertyMap.get(cityProperty, String.class);

            if(city.equals(template)) {
                String date = propertyMap.get(dateProperty, String.class);

                String place = propertyMap.get(placeProperty, String.class);

                eventList.add(date);
                eventList.add(place);

                break;
            }
        }
        response.setContentType("text/html");

        if(eventList.size() == 0) {

            out.write("<p>-</p>");

            out.flush();
        }
        else {
            for(String str : eventList) {
                out.write("<p>" + str + "</p>");
            }
            out.flush();
        }
    }

    private String getPathToPage(SlingHttpServletRequest request) throws MalformedURLException {
        URL url = new URL(request.getHeader("referer"));
        String path = url.getPath();
        PathInfo info = new PathInfo(path);
        return info.getResourcePath() + "/";
    }
}
