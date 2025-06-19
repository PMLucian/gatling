package waitingRoom.utils;

public class TargetEnvResolver {

  // Record to store environment-specific information
  public record EnvInfo(String baseUrl) {}

  // Resolve environment-specific configuration based on the target environment
  public static EnvInfo resolveEnvironmentInfo(String targetEnv) {
    return new EnvInfo("https://stgmy.oneclub.golf");
  }
}
