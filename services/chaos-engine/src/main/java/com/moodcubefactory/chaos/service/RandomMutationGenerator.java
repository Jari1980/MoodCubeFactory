package com.moodcubefactory.chaos.service;

import com.moodcubefactory.chaos.model.CubeEvent;

import java.util.Random;

public class RandomMutationGenerator {

    private final Random rand = new Random();

    public CubeEvent generate() {

        int cubeId = rand.nextInt(100) + 1;

        String[] mutations = {
                "Cube exploded",
                "Cube became sleepy",
                "Cube became radioactive",
                "Cube turned invisible"
        };

        String mutation = mutations[rand.nextInt(mutations.length)];

        return new CubeEvent(
                cubeId,
                "MUTATION",
                mutation
        );
    }
}
