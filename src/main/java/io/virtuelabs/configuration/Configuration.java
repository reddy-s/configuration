package io.virtuelabs.configuration;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

  private static final Logger LOGGER = Logger.getLogger(Configuration.class.getName());

  public static void main(String[] args) throws IOException, InterruptedException {

    LOGGER.log(Level.INFO, "Preparing the CONFIGURATION gRPC server for start up");
    Server server = ServerBuilder.forPort(20001)
      //.addService(new DeviceServiceImpl())
      .build();
    LOGGER.log(Level.INFO, "Starting CONFIGURATION gRPC server");
    server.start();
    LOGGER.log(Level.INFO, "Successfully started CONFIGURATION gRPC server and is running on port 20001");
    Runtime.getRuntime().addShutdownHook( new Thread ( () -> {
      LOGGER.log(Level.INFO,"Received Shutdown Request");
      server.shutdown();
      LOGGER.log(Level.INFO,"Sucessfully stopped CONFIGURATION gRPC server");
    }));

    server.awaitTermination();
  }
}
