package io.appform.dropwizard.demo;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.discovery.bundle.ServiceDiscoveryBundle;
import io.dropwizard.discovery.bundle.ServiceDiscoveryConfiguration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Application class for the service
 */
public class App extends Application<AppConfig> {

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor()
                )
        );

        bootstrap.addBundle(new ServiceDiscoveryBundle<AppConfig>() {
            @Override
            protected ServiceDiscoveryConfiguration getRangerConfiguration(AppConfig appConfig) {
                return appConfig.getDiscovery();
            }

            @Override
            protected String getServiceName(AppConfig appConfig) {
                return "demo";
            }

        });
    }

    @Override
    public void run(AppConfig appConfig, Environment environment) throws Exception {
        environment.jersey().register(new HelloResource(appConfig.getName()));
    }


    public static void main(String[] args) throws Exception {
        App app = new App();
        app.run(args);
    }
}
