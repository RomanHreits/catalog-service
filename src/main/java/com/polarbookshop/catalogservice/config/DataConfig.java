package com.polarbookshop.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@Configuration
@EnableJdbcAuditing
/* Enables auditing features for the data layer, such as tracking creation and modification timestamps.
    This is useful for entities that require auditing information, like createdBy, createdDate, lastModifiedBy, and lastModifiedDate.
 */
public class DataConfig {
}
