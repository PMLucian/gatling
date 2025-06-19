package example;

import static example.endpoints.APIendpoints.withAuthenticationHeader;
import static example.groups.ScenarioGroups.*;
import static example.utils.Config.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class waitingRoom extends Simulation {

  static final HttpProtocolBuilder httpProtocolWithAuthentication =
      withAuthenticationHeader(
          http.baseUrl(baseUrl).authorizationHeader("Basic cmFuZGE6MTUwb3Blbg=="));

  static final ScenarioBuilder scn1 =
      scenario("Scenario 1").exitBlockOnFail().on(authorizeFlow).exitHereIfFailed();

  // Define different load injection profiles
  // Reference: https://docs.gatling.io/reference/script/core/injection/
  static final PopulationBuilder injectionProfile(ScenarioBuilder scn) {
    return switch (testType) {
      case "capacity" ->
          scn.injectOpen(
              incrementUsersPerSec(vu)
                  .times(2)
                  .eachLevelLasting(duration)
                  .separatedByRampsLasting(1)
                  .startingFrom(1));
      case "soak" -> scn.injectOpen(constantUsersPerSec(vu).during(duration));
      case "stress" -> scn.injectOpen(stressPeakUsers(vu).during(duration));
      case "breakpoint" -> scn.injectOpen(rampUsers(vu).during(duration));
      case "ramp-hold" ->
          scn.injectOpen(
              rampUsersPerSec(1).to(vu).during(ramp_duration),
              constantUsersPerSec(vu).during(duration));
      case "smoke" -> scn.injectOpen(atOnceUsers(5));
      default -> scn.injectOpen(atOnceUsers(vu));
    };
  }

  // Define assertions for different test types
  // Reference: https://docs.gatling.io/reference/script/core/assertions/
  //  static final List<Assertion> assertions =
  //      List.of(
  //          global().responseTime().percentile(90.0).lt(500),
  //          global().failedRequests().percent().lt(5.0));

  //  static final List<Assertion> getAssertions() {
  //    return switch (testType) {
  //      case "capacity", "soak", "stress", "breakpoint", "ramp-hold" -> assertions;
  //      case "smoke" -> List.of(global().failedRequests().count().lt(1L));
  //      default -> assertions;
  //    };
  //  }

  // Set up the simulation with scenarios, load profiles, and assertions
  {
    setUp(injectionProfile(scn1)).protocols(httpProtocolWithAuthentication);
  }
}
