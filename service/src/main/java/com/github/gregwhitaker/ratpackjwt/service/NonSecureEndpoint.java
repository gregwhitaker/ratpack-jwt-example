package com.github.gregwhitaker.ratpackjwt.service;

import ratpack.func.Action;
import ratpack.handling.Chain;

import java.util.HashMap;
import java.util.Map;

import static ratpack.jackson.Jackson.json;

/**
 * REST endpoint that is not secured by JWT.
 */
public class NonSecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.get("", ctx -> {
            Map<String, String> response = new HashMap<>();
            response.put("message", "This endpoint is NOT protected by JWT");
            ctx.render(json(response));
        });
    }
}
