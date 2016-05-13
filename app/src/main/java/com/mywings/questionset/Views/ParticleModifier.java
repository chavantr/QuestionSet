package com.mywings.questionset.Views;


public interface ParticleModifier {

    /**
     * modifies the specific value of a particle given the current miliseconds
     *
     * @param particle
     * @param milliseconds
     */
    void apply(Particle particle, long milliseconds);

}
