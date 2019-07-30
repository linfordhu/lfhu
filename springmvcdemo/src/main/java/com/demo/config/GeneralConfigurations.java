package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * general configuration
 * @author hlf
 * @since 2019/07/16 09:42
 */
@Configuration
@Import( {Neo4jConfiguration.class} )
public class GeneralConfigurations {
}
