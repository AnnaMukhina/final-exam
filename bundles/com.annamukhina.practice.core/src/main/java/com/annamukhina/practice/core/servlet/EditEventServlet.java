package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/edit")
public class EditEventServlet extends SlingAllMethodsServlet {
    private final String pathToRootNode = "/apps/finalexam/components/tableComponent/events/";

    private final String dateProperty = "date";

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        LOGGER.debug("EditEventServlet doPost");

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String input = br.readLine();

        String[] inputArray = input.split(" ");

        String id = inputArray[0];

        String newDate = inputArray[1];

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(pathToRootNode + id);

        ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);

        properties.put(dateProperty, newDate);

        resolver.commit();

        PrintWriter out = response.getWriter();

        out.write("{}");

        out.flush();
    }
}
