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
@SlingServlet(paths = "/bin/test/delete")
public class DeleteEventServlet extends SlingAllMethodsServlet {
    private final String path = "/apps/finalexam/components/tableComponent/events/";

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String id = "";

        if(br != null){
            id = br.readLine();
        }

        String nodeName = "event"+id;

        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path+nodeName);

        Node node = resource.adaptTo(Node.class);

        try {
            node.remove();

            resolver.commit();

            PrintWriter out = response.getWriter();

            out.write("{}");

            out.flush();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
}
