package com.migrate.controller;

import com.migrate.utility.CommonConstantsIF;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class TruncateSecondaryDatabaseController implements CommonConstantsIF {

    //TODO : Implement this for testing
    @GetMapping(value = "/truncate/{activationToken}")
    public String migrate(@PathVariable("activationToken") String activationToken)
    {

        return null;
    }
}