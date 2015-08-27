package com.annamukhina.practice.components.model;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.slice.mapper.annotation.JcrProperty;

import com.cognifide.slice.mapper.annotation.SliceResource;
import com.cognifide.slice.api.model.InitializableModel;
import com.google.inject.Inject;


/**
 * @author anna_mukhina
 */
@SliceResource
public class TableComponentModel implements InitializableModel {
    private static final Logger logger = LoggerFactory.getLogger(TableComponentModel.class);

    @JcrProperty
    private String number;

    public String getNumber() {
        return number;
    }

    @JcrProperty("jcr:lastModified")
    private Date lastModified;

    @Inject
    public TableComponentModel(final Resource resource) {
        logger.debug(resource.getPath());
    }

    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public void afterCreated() {
        logger.debug("Entering TableComponentModel.afterCreated");
        if (number == null) {
            logger.debug("There is no text property in the resource");
        }
        if (lastModified == null) {
            logger.debug("There is no lastModifiedBy property in the resource");
        }
    }
}
