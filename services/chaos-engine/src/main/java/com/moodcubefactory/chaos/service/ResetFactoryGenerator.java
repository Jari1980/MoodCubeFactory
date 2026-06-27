package com.moodcubefactory.chaos.service;

import com.moodcubefactory.chaos.model.CubeEvent;

public class ResetFactoryGenerator {
    public CubeEvent generate() {

        return new CubeEvent(
                0,
                "RESET_FACTORY",
                "Factory reset requested"
        );
    }
}
