/* @Author: Kethan Kumar */

package org;

import org.apache.log4j.Logger;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());
        logger.fatal("Uncaught Exception!!", e);
    }

}
