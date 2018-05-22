package com.zhyxcs.xxzz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "perpage")
@PropertySource(value = "classpath:application.properties")
public class PageConfig {
    private String displayCount;

    public String getDisplayCount() {
        return displayCount;
    }

    public void setDisplayCount(String displayCount) {
        this.displayCount = displayCount;
    }
}
