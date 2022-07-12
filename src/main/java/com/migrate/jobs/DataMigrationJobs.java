package com.migrate.jobs;

import com.migrate.controller.DataMigrationController;
import com.migrate.utility.CommonConstantsIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DataMigrationJobs implements CommonConstantsIF {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataMigrationController dataMigrationController;

    @Scheduled(fixedRateString = "${scheduler.jobs.fixedRate}") //Pick-up the expression from application.properties
    public void MigrateData()
    {
        logger.info("INFO : MigrateData called "
                        + logger.isInfoEnabled());
        logger.trace("TRACE : MigrateData called "
                + logger.isTraceEnabled());
        logger.warn("WARN : MigrateData called "
                + logger.isWarnEnabled());
        logger.error("ERROR : MigrateData called "
                + logger.isErrorEnabled());
        logger.debug("DEBUG : MigrateData called "
                + logger.isDebugEnabled());
        logger.info("Job Triggered at " + LocalTime.now());
        String jobResponse = dataMigrationController.migrate(CommonConstantsIF.VALIDATION_TOKEN);
        logger.info(jobResponse);
        logger.info("Job Ending at  " + LocalTime.now());
    }
}