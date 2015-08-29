package com.annamukhina.practice.core.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONException;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        Node root = resource.adaptTo(Node.class);

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";

        if(br != null){
            json = br.readLine();
        }

        try {
            long numberOfNodes = root.getNodes().getSize();

            numberOfNodes++;

            String name = "event"+numberOfNodes;

            Node event  = root.addNode(name);

            JSONObject jsonObject = new JSONObject(json);
            String date = jsonObject.getString("date");
            String place = jsonObject.getString("place");
            String city = jsonObject.getString("city");

            event.setProperty("date",date );
            event.setProperty("place", place);
            event.setProperty("city", city);

            resolver.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (PathNotFoundException e) {
            e.printStackTrace();
        } catch (ItemExistsException e) {
            e.printStackTrace();
        } catch (ValueFormatException e) {
            e.printStackTrace();
        } catch (VersionException e) {
            e.printStackTrace();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        } catch (LockException e) {
            e.printStackTrace();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
 //TODO rename nodes because of problems with deletion

    }
}
