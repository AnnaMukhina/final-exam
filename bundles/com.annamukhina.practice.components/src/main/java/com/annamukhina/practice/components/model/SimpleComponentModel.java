package com.annamukhina.practice.components.model;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.slice.mapper.annotation.JcrProperty;

import com.cognifide.slice.mapper.annotation.SliceResource;
import com.cognifide.slice.api.model.InitializableModel;
import com.google.inject.Inject;

@SliceResource
public class SimpleComponentModel implements InitializableModel {

    private static final Logger logger = LoggerFactory.getLogger(SimpleComponentModel.class);

    @JcrProperty
    private String text;

    @JcrProperty("jcr:lastModified")
    private Date lastModified;

    @Inject
    public SimpleComponentModel(final Resource resource) {
        logger.debug(resource.getPath());
    }

    public String getText() {
        return text;
    }

    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public void afterCreated() {
        logger.debug("Entering SimpleComponentModel.afterCreated");
        if (text == null) {
            logger.debug("There is no text property in the resource");
        }
        if (lastModified == null) {
            logger.debug("There is no lastModifiedBy property in the resource");
        }
    }
}
