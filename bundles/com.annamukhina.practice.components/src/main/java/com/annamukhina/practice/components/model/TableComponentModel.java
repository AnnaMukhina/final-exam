package com.annamukhina.practice.components.model;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cognifide.slice.mapper.annotation.SliceResource;
import com.cognifide.slice.api.model.InitializableModel;
import com.google.inject.Inject;


/**
 * @author anna_mukhina
 */
@SliceResource
public class TableComponentModel implements InitializableModel {
    private static final Logger logger = LoggerFactory.getLogger(TableComponentModel.class);

    @Inject
    public TableComponentModel(final Resource resource) {
        logger.debug(resource.getPath());
    }

    @Override
    public void afterCreated() {
        logger.debug("Entering TableComponentModel.afterCreated");
    }
}
