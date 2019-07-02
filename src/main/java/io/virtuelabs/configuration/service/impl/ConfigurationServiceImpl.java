package io.virtuelabs.configuration.service.impl;

import io.grpc.stub.StreamObserver;
import io.virtuelabs.contract.ConfigRequest;
import io.virtuelabs.contract.ConfigResponse;
import io.virtuelabs.contract.ConfigurationServiceGrpc;
import io.virtuelabs.contract.DeviceType;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigurationServiceImpl extends ConfigurationServiceGrpc.ConfigurationServiceImplBase {

  private static final Logger LOGGER = Logger.getLogger(ConfigurationServiceGrpc.class.getName());

  private static long addLatencyMs = Long.parseLong(System.getenv("IOT_CONFIGURATION_SERVICE_ADD_LATENCY"));

  @Override
  public void sendEvent(ConfigRequest request, StreamObserver<ConfigResponse> responseObserver) {

    LOGGER.log(Level.INFO, "Received request from DeviceService for device: {0}", request.getDeviceId());

    int beatFrequency = 0;
    try {
      LOGGER.log(Level.INFO, "Processing request from DeviceService for device: {0}", request.getDeviceId());
      Thread.sleep(addLatencyMs);
      if (request.getDeviceType() == DeviceType.EDISON){
        beatFrequency = 5;
      } else if (request.getDeviceType() == DeviceType.ESP8266){
        beatFrequency = 10;
      }
      responseObserver.onNext(
        ConfigResponse.newBuilder()
          .setBeatFrequency(beatFrequency)
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
