package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;

import javax.jcr.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.sling.commons.json.*;

/**
 * @author anna_mukhina
 */
@SlingServlet(paths = "/bin/test/add")
public class AddEventServlet extends SlingAllMethodsServlet {
    private final String path = "/apps/finalexam/components/tableComponent/events";

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        ResourceResolver resolver = request.getResourceResolver();

        Resource resource = resolver.getResource(path);

        Resource serviceRes = resolver.getResource("/apps/finalexam/components/tableComponent/service");

        Node root = resource.adaptTo(Node.class);

        Node service = serviceRes.adaptTo(Node.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";

        if(br != null){
            json = br.readLine();
        }

        try {
            long numberOfNodes = service.getProperty("numberOfNodes").getLong();

            numberOfNodes++;

            String name = "event"+numberOfNodes;

            service.setProperty("numberOfNodes", numberOfNodes);

            Node event  = root.addNode(name);

            JSONObject jsonObject = new JSONObject(json);
            String date = jsonObject.getString("date");
            String place = jsonObject.getString("place");
            String city = jsonObject.getString("city");
            String latitude = jsonObject.getString("latitude");
            String longitude = jsonObject.getString("longitude");

            event.setProperty("date",date );
            event.setProperty("place", place);
            event.setProperty("city", city);
            event.setProperty("latitude", latitude);
            event.setProperty("longitude", longitude);
            event.setProperty("id", numberOfNodes);

            resolver.commit();

            PrintWriter out = response.getWriter();

            out.write("{}");

            out.flush();
        } catch (JSONException | RepositoryException e) {
            e.printStackTrace();
        }
    }
}
