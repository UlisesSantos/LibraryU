package com.school.library.Java.LibraryU.common;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.configuration2.reloading.ReloadingController;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Class to get variables from application.properties
 * */
public final class App {

    // application.properties file name
    private static final String APPLICATION_PROPERTIES = "application.properties";

    // Application name key
    private static final String APPLICATION_NAME_KEY = "spring.application.name";

    // Instance name key
    private static final String INSTANCE_NAME_KEY = "INSTANCE_NAME";
    // Logs path key
    private static final String LOGS_PATH_KEY = "LOGS_PATH";
    // application-name.properties path key
    private static final String PROPERTIES_PATH_KEY = "PROPERTIES_PATH";

    // Object to get application.properties values
    private static PropertiesConfiguration configuration;
    static {
        try{
            Parameters params = new Parameters();
            ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                    new ReloadingFileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName(APPLICATION_PROPERTIES)
                                    .setEncoding(StandardCharsets.UTF_8.name())
                                    .setThrowExceptionOnMissing(true)
                                    .setListDelimiterHandler(null)
                            );
            ReloadingController controller = builder.getReloadingController();
            PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(
                    controller,
                    null,
                    5,
                    TimeUnit.SECONDS
            );
            trigger.start();
            configuration = builder.getConfiguration();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Function to get a property by key
     * @param key Key of the property
     * @return property
     * */
    public static String getProperty(String key){
        return configuration.getString(key);
    }

    // Application name value
    public static final String APPLICATION_NAME = getProperty(APPLICATION_NAME_KEY);
    // Logs path value
    public static final String LOGS_PATH = System.getProperty(LOGS_PATH_KEY);
    // Instance name value
    public static final String INSTANCE_NAME = System.getProperty(INSTANCE_NAME_KEY);
    // Properties path value
    public static final String PROPERTIES_PATH = System.getProperty(PROPERTIES_PATH_KEY);
}
