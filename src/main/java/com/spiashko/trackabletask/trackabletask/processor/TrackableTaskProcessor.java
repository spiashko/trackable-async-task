package com.spiashko.trackabletask.trackabletask.processor;

import net.jodah.typetools.TypeResolver;

public abstract class TrackableTaskProcessor<T> {

    private final Class<T> inputClass;

    @SuppressWarnings("unchecked")
    public TrackableTaskProcessor() {
        Class<?>[] typeArguments = TypeResolver.resolveRawArguments(TrackableTaskProcessor.class, this.getClass());
        this.inputClass = (Class<T>) typeArguments[0];
    }

    public abstract void process(T input);

    public abstract String getName();

    public Class<T> getInputClass() {
        return inputClass;
    }

}
