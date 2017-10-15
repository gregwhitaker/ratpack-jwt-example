package com.github.gregwhitaker.ratpackjwt.service;

import org.pac4j.core.profile.UserProfile;
import org.pac4j.http.client.direct.ParameterClient;
import ratpack.func.Action;
import ratpack.handling.Chain;
import ratpack.jackson.Jackson;
import ratpack.pac4j.RatpackPac4j;

import java.util.HashMap;
import java.util.Map;

import static ratpack.jackson.Jackson.json;

/**
 * REST endpoint that is secured by JWT.
 */
public class SecureEndpoint implements Action<Chain> {

    @Override
    public void execute(Chain chain) throws Exception {
        chain.prefix("secure", c -> {
            c.all(RatpackPac4j.requireAuth(ParameterClient.class));
            c.get(ctx -> {
                UserProfile profile = ctx.get(UserProfile.class);

                Map<String, Object> userInfo = new HashMap<>();
                userInfo.put("email", profile.getAttribute("email"));
                userInfo.put("displayName", profile.getAttribute("display_name"));
                userInfo.put("roles", profile.getRoles());

                Map<String, Object> response = new HashMap<>();
                response.put("message", "This endpoint is protected by JWT");
                response.put("user", userInfo);
                
                ctx.render(json(response));
            });
        });
    }
}
