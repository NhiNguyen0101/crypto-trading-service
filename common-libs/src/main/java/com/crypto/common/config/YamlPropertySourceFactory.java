package com.crypto.common.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

/**
 * Enables to use @{@link org.springframework.context.annotation.PropertySource} in combination with
 * YML files. Example:<br> {@code
 *
 * @PropertySource(value = "classpath:file.yml", factory = YamlPropertySourceFactory.class) }
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    @SuppressWarnings({"ConstantConditions"})
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(encodedResource.getResource());
        Properties properties = factory.getObject();
        return new PropertiesPropertySource(encodedResource.getResource().getFilename(), properties);
    }
}
