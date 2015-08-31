package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/delete")
public class DeleteEventServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events/";

    private final String rootNodeName = "event";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOGGER.debug("DeleteEventServlet doPost");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String id = br.readLine();

        String nodeName = rootNodeName + id;

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(pathToRootNode + nodeName);

        Node node = resource.adaptTo(Node.class);

        try {
            node.remove();

            resolver.commit();

            PrintWriter out = response.getWriter();

            out.write("{}");

            out.flush();
        } catch (RepositoryException e) {
            LOGGER.error("Exception!", e);
        }
    }
}
