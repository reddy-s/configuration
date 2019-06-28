package io.virtuelabs.configuration.impl;

import io.grpc.stub.StreamObserver;
import io.virtuelabs.contract.ConfigRequest;
import io.virtuelabs.contract.ConfigResponse;
import io.virtuelabs.contract.ConfigurationServiceGrpc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationServiceImpl extends ConfigurationServiceGrpc.ConfigurationServiceImplBase {

  private static final Logger LOGGER = Logger.getLogger(ConfigurationServiceGrpc.class.getName());

  private static long addLatencyMs = Long.parseLong(System.getenv("IOT_CONFIGURATION_SERVICE_ADD_LATENCY"));

  @Override
  public void sendEvent(ConfigRequest request, StreamObserver<ConfigResponse> responseObserver) {

    LOGGER.log(Level.INFO, "Received request from DeviceService for device: {0}", request.getDeviceId());
    try {
      LOGGER.log(Level.INFO, "Processing request from DeviceService for device: {0}", request.getDeviceId());
      Thread.sleep(addLatencyMs);
      responseObserver.onNext(
        ConfigResponse.newBuilder()
          .setBeatFrequency(10)
          .build()
      );
    } catch (RuntimeException e) {
      responseObserver.onError(e);
      throw e;
    } catch (InterruptedException e) {
      responseObserver.onError(e);
    }

    LOGGER.log(Level.INFO, "Responding to request from DeviceService for device: {0}", request.getDeviceId());
    responseObserver.onCompleted();
  }
}
