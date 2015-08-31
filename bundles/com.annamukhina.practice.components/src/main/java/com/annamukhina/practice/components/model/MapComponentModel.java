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
    private static final Logger logger = LoggerFactory.getLogger(MapComponentModel.class);

    @JcrProperty
    private String height;

    @JcrProperty
    private String width;


    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    @Inject
    public MapComponentModel(final Resource resource) {
        logger.debug(resource.getPath());
    }

    @Override
    public void afterCreated() {
        logger.debug("Entering MapComponentModel.afterCreated");

        if (height == null) {
            logger.debug("There is no height property in the resource");
        }
        if (width == null) {
            logger.debug("There is no width property in the resource");
        }
    }
}
