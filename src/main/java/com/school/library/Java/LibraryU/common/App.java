package com.school.library.Java.LibraryU.common;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.configuration2.reloading.ReloadingController;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public final class App {

    //File Properties Name
    private static final String APPLICATION_PROPERTIES = "application.properties";

    // Application Name Key
    private static final String APPLICATION_NAME_KEY = "spring.application.name";

    // Private Keys from Environment Variables
    private static final String INSTANCE_NAME_KEY = "INSTANCE_NAME";
    private static final String LOGS_PATH_KEY = "LOGS_PATH";

    private static PropertiesConfiguration configuration;

    //Object to Get Environment Variables
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
     * Function to get a property by a key
     * @param key
     * @return property
     * */
    public static String getProperty(String key){
        return configuration.getString(key);
    }

    //Application Name
    public static final String APPLICATION_NAME = getProperty(APPLICATION_NAME_KEY);
    //Logs Path
    public static final String LOGS_PATH = System.getProperty(LOGS_PATH_KEY);
    //Instance Name
    public static final String INSTANCE_NAME = System.getProperty(INSTANCE_NAME_KEY);


}
