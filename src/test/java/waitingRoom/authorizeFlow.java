package waitingRoom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static waitingRoom.endpoints.APIendpoints.withAuthenticationHeader;
import static waitingRoom.groups.ScenarioGroups.*;
import static waitingRoom.utils.Config.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class authorizeFlow extends Simulation {

  static final HttpProtocolBuilder httpProtocolWithAuthentication =
      withAuthenticationHeader(
          http.baseUrl(baseUrl).authorizationHeader("Basic cmFuZGE6MTUwb3Blbg=="));

  static final ScenarioBuilder scn =
      scenario("Authorize").exitBlockOnFail().on(authorizeFlow).exitHereIfFailed();

  {
    setUp(
        scn.injectOpen(atOnceUsers(0), rampUsers(10).during(10))
            .protocols(httpProtocolWithAuthentication));
  }
}
