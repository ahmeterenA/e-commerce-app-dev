package com.aerenarslanoglu.ecommerce.handler;

import java.util.Map;

public record ErrorDetails(
        Map<String, String> errors,
        String path

) {
}
