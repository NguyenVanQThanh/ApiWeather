package com.skyapi.weatherforecast;

import java.io.IOException;

public class GeolocationException extends Throwable {
    public GeolocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeolocationException(String message) {
        super(message);
    }
}
