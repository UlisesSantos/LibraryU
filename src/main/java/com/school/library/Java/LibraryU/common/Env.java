package com.school.library.Java.LibraryU.common;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.configuration2.reloading.ReloadingController;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * Class to get the variables from application-name.properties
 * */
public final class Env {


    private static PropertiesConfiguration configuration;

    //Object to Get Environment Variables
    static {
        try{
            Parameters params = new Parameters();
            ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder =
                    new ReloadingFileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
                            .configure(params.properties()
                                    .setFileName(String.format("%s/%s.properties", App.PROPERTIES_PATH, App.APPLICATION_NAME))
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
     * Method to get a property
     * @param key Key of the property
     * */
    public static String getProperty(String key) {return configuration.getString(key);}

    /**
     * Method to get a property by prefix
     * @param key key of the property
     * @param prefix prefix of the property
     * */
    public static String getPropertyByPrefix(String prefix, String key){
        String instanceKey = String.format("%s.%s.%s", App.INSTANCE_NAME,prefix,key);
        String property = (String) configuration.getString(instanceKey);
        if (property == null || property.trim().isEmpty()){
            throw new RuntimeException("Can't find the property: " + instanceKey);
        }
        return property;
    }



}
