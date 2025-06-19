package waitingRoom.endpoints;

import static waitingRoom.utils.Keys.*;

import io.gatling.javaapi.http.*;
import java.util.Optional;

public class APIendpoints {

  // Add authentication header if an access token exists in the session
  // Reference:
  // https://docs.gatling.io/reference/script/protocols/http/protocol/#header
  public static final HttpProtocolBuilder withAuthenticationHeader(
      HttpProtocolBuilder protocolBuilder) {
    return protocolBuilder.header(
        "Authorization", session -> Optional.ofNullable(session.getString(BASIC_AUTH)).orElse(""));
  }
}
