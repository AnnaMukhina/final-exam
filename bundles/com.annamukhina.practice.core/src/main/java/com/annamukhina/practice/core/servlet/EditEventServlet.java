package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/edit")
public class EditEventServlet extends SlingAllMethodsServlet {
    private final String path = "/apps/finalexam/components/tableComponent/events/";

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String input = br.readLine();

        System.out.println(input);

        String[] inputArray = input.split(" ");

        String id = inputArray[0];

        String newDate = inputArray[1];

        String nodeName = "event"+id;

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path+nodeName);

        Node node = resource.adaptTo(Node.class);

        try {
            node.setProperty("date", newDate);

            resolver.commit();

            PrintWriter out = response.getWriter();

            out.write("{}");

            out.flush();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
