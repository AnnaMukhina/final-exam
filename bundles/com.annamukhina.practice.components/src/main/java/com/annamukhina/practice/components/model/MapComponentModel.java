package com.annamukhina.practice.components.model;

import com.cognifide.slice.api.model.InitializableModel;
import com.cognifide.slice.mapper.annotation.JcrProperty;
import com.cognifide.slice.mapper.annotation.SliceResource;
import com.google.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anna_mukhina
 */

@SliceResource
public class MapComponentModel implements InitializableModel {
    private static final Logger logger = LoggerFactory.getLogger(SimpleComponentModel.class);

    @JcrProperty
    private String text;


    public String getText() {
        return text;
    }

    @Inject
    public MapComponentModel(final Resource resource) {
        logger.debug(resource.getPath());
    }

    @Override
    public void afterCreated() {
        logger.debug("Entering SimpleComponentModel.afterCreated");
    }
}
