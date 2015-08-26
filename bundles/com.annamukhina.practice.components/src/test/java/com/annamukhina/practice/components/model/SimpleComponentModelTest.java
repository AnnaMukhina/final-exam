package com.annamukhina.practice.components.model;

import org.apache.sling.api.resource.Resource;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SimpleComponentModelTest {

    @Test
    public void getTextTest() {
        Resource mockedResource = mock(Resource.class);
        SimpleComponentModel bean = new SimpleComponentModel(mockedResource);
        Whitebox.setInternalState(bean, "text", "Some dummy test");
        assertEquals("Some dummy test", bean.getText());
    }
 }