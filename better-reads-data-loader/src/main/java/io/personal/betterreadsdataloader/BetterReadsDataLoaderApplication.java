package io.personal.betterreadsdataloader;

import io.personal.betterreadsdataloader.connection.DataStaxAstraProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@EnableConfigurationProperties(DataStaxAstraProperties.class)
@SpringBootApplication
public class BetterReadsDataLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterReadsDataLoaderApplication.class, args);
	}

	@Bean
	CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties dataStaxAstraProperties){
		Path bundle = dataStaxAstraProperties.getSecureConnectBundle().toPath();
		return cqlSessionBuilder -> cqlSessionBuilder.withCloudSecureConnectBundle(bundle);
	}
}
