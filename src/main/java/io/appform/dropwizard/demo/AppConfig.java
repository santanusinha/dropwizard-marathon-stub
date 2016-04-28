package io.appform.dropwizard.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.discovery.bundle.ServiceDiscoveryConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Application config.
 */
public class AppConfig extends Configuration {
    @JsonProperty
    @NotNull
    @Valid
    private ServiceDiscoveryConfiguration discovery = new ServiceDiscoveryConfiguration();

    public ServiceDiscoveryConfiguration getDiscovery() {
        return discovery;
    }

}
