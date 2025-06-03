package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/* ConfigurationProperties annotation also ensures that this bean will be listening to
    RefreshScopeRefreshEvent(triggered by actions like POST /actuator/refresh)
 */
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    /**
     * Greeting message for the Polar Bookshop users.
     */
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
