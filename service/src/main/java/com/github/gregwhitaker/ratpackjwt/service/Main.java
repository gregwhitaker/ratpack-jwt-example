package com.github.gregwhitaker.ratpackjwt.service;

import org.pac4j.http.client.direct.HeaderClient;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import ratpack.guice.Guice;
import ratpack.pac4j.RatpackPac4j;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;
import ratpack.session.SessionModule;

import java.util.Arrays;

/**
 * Starts the Ratpack JWT Example Application.
 */
public class Main {

    public static void main(String... args) throws Exception {
        String secret = System.getProperty("JWT_SHARED_SECRET");

        final SignatureConfiguration signatureConfiguration = new SecretSignatureConfiguration(secret);
        final EncryptionConfiguration encryptionConfiguration = new SecretEncryptionConfiguration(secret);
        final JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(Arrays.asList(signatureConfiguration), Arrays.asList(encryptionConfiguration));

        // Use this if you are going to pass the JWT as a URL parameter
        final ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator);

        // Use this if you are going to pass the JWT in the Authorization header
        final HeaderClient headerClient = new HeaderClient("Authorization", "Bearer ", jwtAuthenticator);

        parameterClient.setSupportGetRequest(true);
        parameterClient.setSupportPostRequest(false);

        RatpackServer.start(s -> s
                .serverConfig(c -> c
                        .baseDir(BaseDir.find()).build())
                .registry(Guice.registry(b -> {
                    b.bind(NonSecureEndpoint.class);
                    b.bind(SecureEndpoint.class);
                    b.module(SessionModule.class);
                }))
                .handlers(chain -> chain
                        .all(RatpackPac4j.authenticator(parameterClient, headerClient))
                        .insert(NonSecureEndpoint.class)
                        .insert(SecureEndpoint.class)
                )
        );
    }
}
