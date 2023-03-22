package com.annadach.tests.config;

@CredentialsConfig.Sources({"classpath:config/properties"})
public interface CredentialsConfig extends org.aeonbits.owner.Config{

    @Key("mail")
    String mail();

    @Key("password")
    String password();

    @Key("title")
    String title();

    @Key("reviewText")
    String reviewText();

}
