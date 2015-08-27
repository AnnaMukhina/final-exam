package com.annamukhina.practice.core.bundle;

import com.cognifide.slice.api.injector.InjectorRunner;
import com.cognifide.slice.commons.SliceModulesFactory;
import com.cognifide.slice.cq.module.CQModulesFactory;
import com.google.inject.Module;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class Activator implements BundleActivator {

    private static final String BUNDLE_NAME_FILTER = "com\\.yurishchev\\.finalexam\\..*";

    private static final String BASE_PACKAGE = "com.annamukhina.practice";

    private static final String INJECTOR_NAME = "finalexam";

    private static final Logger logger = LoggerFactory.getLogger(Activator.class);

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        logger.debug("Entering BundleActivator.start");
        final InjectorRunner injectorRunner =
                new InjectorRunner(bundleContext, INJECTOR_NAME, BUNDLE_NAME_FILTER, BASE_PACKAGE);
        logger.debug("Configuring Injector - {}, for bundles - {}",
                INJECTOR_NAME, BUNDLE_NAME_FILTER);
        final List<Module> sliceModules = SliceModulesFactory.createModules(bundleContext);
        final List<Module> cqModules = CQModulesFactory.createModules();
        final List<Module> customModules = createCustomModules();

        injectorRunner.installModules(sliceModules);
        injectorRunner.installModules(cqModules);
        injectorRunner.installModules(customModules);

        logger.debug("Starting injector runner");
        injectorRunner.start();
        logger.debug("Leaving BundleActivator.start");

        Events events = new Events();
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }

    private List<Module> createCustomModules() {
        //populate the list with your modules
        return new ArrayList<Module>();
    }
}
