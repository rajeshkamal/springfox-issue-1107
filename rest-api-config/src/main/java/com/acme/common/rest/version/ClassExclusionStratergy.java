package com.acme.common.rest.version;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ClassExclusionStratergy implements ExclusionStrategy {
    private final Class<?> typeToSkip;

    public ClassExclusionStratergy(Class<?> typeToSkip) {
        this.typeToSkip = typeToSkip;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return (clazz == typeToSkip);
    }

    @Override
    public boolean shouldSkipField(FieldAttributes arg0) {
        return false;
    }
}