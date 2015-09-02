package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/find")
public class FindEventServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events";

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOGGER.debug("FindEventServlet doPost");

        String date = "";
        String place = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String template =  br.readLine();

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(pathToRootNode);

        Node root = resource.adaptTo(Node.class);

        PrintWriter out = response.getWriter();

        try {
            NodeIterator iterator = root.getNodes();

            while(iterator.hasNext()) {
                Node node = iterator.nextNode();

                String city = node.getProperty(cityProperty).getString();

                if(city.equals(template)) {
                    date = node.getProperty(dateProperty).getString();

//                    System.out.println(date);

                    place = node.getProperty(placeProperty).getString();

                    break;
                }
            }
            response.setContentType("text/html");

            if(dateProperty.equals(null)) {

                out.write("<p>There is no concerts in this town</p>");

                out.flush();
            }
            else {
                out.write("<p>"+ date+ "</p><p>" + place + "</p>");

                out.flush();
            }
        } catch (RepositoryException e) {
            LOGGER.error("Exception!", e);
        }
    }
}
