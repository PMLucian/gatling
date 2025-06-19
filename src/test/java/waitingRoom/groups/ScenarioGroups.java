package waitingRoom.groups;

import static io.gatling.javaapi.core.CoreDsl.*;
import static waitingRoom.endpoints.WebEndpoints.*;

import io.gatling.javaapi.core.*;

public class ScenarioGroups {

  // Define authentication process
  public static final ChainBuilder authorizeFlow = group("authenticate").on(authorizeEndpoint);
}
