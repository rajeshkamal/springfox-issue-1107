package com.acme.common.rest.version;

import java.util.List;

import com.google.common.primitives.Doubles;

public final class ApiVersion {

    private ApiVersion() {
    }

    public static final double VERSION_1_0 = 1.0;
    public static final double VERSION_2_0 = 2.0;

    protected static final List<Double> versions = Doubles.asList(VERSION_1_0, VERSION_2_0);
}