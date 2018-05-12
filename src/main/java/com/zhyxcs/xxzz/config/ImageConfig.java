package com.zhyxcs.xxzz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "image")
@PropertySource(value = "classpath:application.properties")
public class ImageConfig {
    private String basePath;
    private String licenceBasePath;

    public String getLicenceBasePath() {
        return licenceBasePath;
    }

    public void setLicenceBasePath(String licenceBasePath) {
        this.licenceBasePath = licenceBasePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }
}
