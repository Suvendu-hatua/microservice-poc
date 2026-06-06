package org.programming.microservices.configurationserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServerApplication {
  public static void main(String[] args) {
    System.out.println("Hello, World!");
  }
}