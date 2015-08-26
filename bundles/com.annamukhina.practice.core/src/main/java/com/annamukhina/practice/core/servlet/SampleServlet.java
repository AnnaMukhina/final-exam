package com.annamukhina.practice.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.annamukhina.practice.core.configuration.SampleConfiguration;

@SlingServlet(paths = "/bin/test/sample")
@Properties({@Property(name = Constants.SERVICE_DESCRIPTION, value = "Sample Servlet"),
             @Property(name = Constants.SERVICE_VENDOR, value = "<Your Company>")})
public class SampleServlet extends SlingAllMethodsServlet {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SlingAllMethodsServlet.class);

    @Reference
    private SampleConfiguration config;

    private static final long serialVersionUID = -1382695992935203054L;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        LOGGER.debug("SampleServlet doGet");
        response.setContentType("text/html");
        try {
            PrintWriter out = response.getWriter();
            out.println("<h1>Sample Serlvet</h1>");
            String instanceName = config.getInstanceName();
            if (StringUtils.isNotEmpty(instanceName)) {
                out.println(instanceName);
            }
            out.flush();
        } catch (IOException e) {
            LOGGER.error("Exception!", e);
        }

    }

}
