package br.com.msclientes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition
public class MsClientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClientesApplication.class, args);
    }

}
