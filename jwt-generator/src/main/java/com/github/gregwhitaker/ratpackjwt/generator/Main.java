package com.github.gregwhitaker.ratpackjwt.generator;

import org.pac4j.core.profile.CommonProfile;
import org.pac4j.core.profile.definition.CommonProfileDefinition;
import org.pac4j.jwt.config.encryption.EncryptionConfiguration;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.profile.JwtGenerator;

/**
 * Generates JSON Web Tokens to use for authenticating with the Ratpack service.
 */
public class Main {

    public static void main(String... args) {
        String email = args[0];
        String displayName = args[1];
        String role = args[2];
        String secret = args[3];

        final SignatureConfiguration signatureConfiguration = new SecretSignatureConfiguration(secret);
        final EncryptionConfiguration encryptionConfiguration = new SecretEncryptionConfiguration(secret);

        CommonProfile profile = new CommonProfile();
        profile.addAttribute(CommonProfileDefinition.EMAIL, email);
        profile.addAttribute(CommonProfileDefinition.DISPLAY_NAME, displayName);
        profile.addRole(role);

        JwtGenerator generator = new JwtGenerator(signatureConfiguration, encryptionConfiguration);
        String jwt = generator.generate(profile);

        System.out.println();
        System.out.println("JWT: " + jwt);
    }
}
