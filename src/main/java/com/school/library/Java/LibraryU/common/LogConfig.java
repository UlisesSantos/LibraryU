package com.school.library.Java.LibraryU.common;

import org.apache.logging.log4j.ThreadContext;

import java.util.UUID;

/**
 * Configuration Log4j2
 * */
public class LogConfig {

    // Constant component
    private static final String COMPONENT = "component";

    //Constant transaction id
    private static final String TRANSACTION_ID = "transactionId";

    private LogConfig(){

    }

    /**
     * Initialize log4j2 by default
     * */
    public static void initLog4j2(){
        ThreadContext.put(COMPONENT, App.APPLICATION_NAME);
        ThreadContext.put(TRANSACTION_ID, UUID.randomUUID().toString());
    }

    /**
     * Initialize log4j2 with personalized parameters
     * */
    public static synchronized void initLog4j2(String component, String transactionId){
        ThreadContext.put(COMPONENT, component);
        ThreadContext.put(TRANSACTION_ID, transactionId);
    }
}
