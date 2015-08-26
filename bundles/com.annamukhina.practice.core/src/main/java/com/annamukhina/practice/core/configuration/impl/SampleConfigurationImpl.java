package com.annamukhina.practice.core.configuration.impl;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Modified;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.annamukhina.practice.core.configuration.SampleConfiguration;

@Component(immediate = true, label = "Sample configuration", metatype = true)
@Service
public class SampleConfigurationImpl implements SampleConfiguration {

    protected String instanceName;

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleConfigurationImpl.class);

    @Property(label = "Instance name",
              description = "Instance name for sample configuration example")
    private static final String INSTANCE_NAME = "instance.name";

    @Override
    public String getInstanceName() {
        return instanceName;
    }

    @Activate
    @Modified
    public synchronized void configure(ComponentContext ctx, final Map<String, Object> props) {
        LOGGER.debug("SampleConfigurationImpl - configure");
        if (props != null) {
            this.instanceName =
                (String) (props.get(INSTANCE_NAME) != null ? props.get(INSTANCE_NAME) : "");
        }
    }

}
