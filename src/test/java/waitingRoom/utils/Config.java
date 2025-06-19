package waitingRoom.utils;

import java.time.Duration;

public class Config {

  // Define the target environment (default: DEV)
  // Reference:
  // https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
  public static final String targetEnv = System.getProperty("targetEnv", "DEV");

  // Load testing configuration
  public static final int vu = Integer.getInteger("vu", 3); // Number of virtual users
  public static final Duration duration =
      Duration.ofMinutes(Integer.getInteger("durationMinutes", 1)); // Test
  // duration in
  // minutes
  public static final Duration ramp_duration =
      Duration.ofMinutes(Integer.getInteger("rampDurationMinutes", 1)); // Ramp-up
  public static final int minPauseSec =
      Integer.getInteger("minPauseSec", 5); // Minimum pause between actions
  public static final int maxPauseSec =
      Integer.getInteger("maxPauseSec", 15); // Maximum pause between actions
  public static final String testType =
      System.getProperty("testType", "smoke"); // Test type (default: smoke)
  public static final TargetEnvResolver.EnvInfo envInfo =
      TargetEnvResolver.resolveEnvironmentInfo(targetEnv);
  public static final String baseUrl = envInfo.baseUrl(); // Base API URL
}
