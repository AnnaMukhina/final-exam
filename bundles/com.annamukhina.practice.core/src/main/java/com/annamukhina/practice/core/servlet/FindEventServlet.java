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
    private final String path = "/apps/finalexam/components/tableComponent/events";

    private final String dateProperty = "date";

    private final String placeProperty = "place";

    private final String cityProperty = "city";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOGGER.debug("FindEventServlet doGet");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String template = br.readLine();

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path);

        Node root = resource.adaptTo(Node.class);

        PrintWriter out = response.getWriter();

        try {
            NodeIterator iterator = root.getNodes();

            JSONObject concert = new JSONObject();

            while(iterator.hasNext()) {
                Node node = iterator.nextNode();

                String city = node.getProperty(cityProperty).getString();

                if(city.equals(template)) {
                    String date = node.getProperty(dateProperty).getString();
                    String place = node.getProperty(placeProperty).getString();

                    concert.append(dateProperty, date);

                    concert.append(placeProperty, place);

                    break;
                }
            }
            if(concert.isNull(dateProperty)) {
                response.setContentType("text/html");

                out.write("There is no concerts in this town :(");

                out.flush();
            }
            else {
                response.setContentType("application/json");
                
                out.write(concert.toString());

                out.flush();
            }
        } catch (RepositoryException | JSONException e) {
            LOGGER.error("Exception!", e);
        }
    }
}
