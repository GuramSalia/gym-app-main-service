package com.epam.gymappmainservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

//to test externalizing some properties
@ConfigurationProperties("main-service")
public class WConfigurationExternalized {
    private String mainServiceTestParam;

    public WConfigurationExternalized() {
    }

    public WConfigurationExternalized(String mainServiceTestParam) {
        this.mainServiceTestParam = mainServiceTestParam;
    }

    public String getMainServiceTestParam() {
        return mainServiceTestParam;
    }

    public void setMainServiceTestParam(String mainServiceTestParam) {
        this.mainServiceTestParam = mainServiceTestParam;
    }
}
