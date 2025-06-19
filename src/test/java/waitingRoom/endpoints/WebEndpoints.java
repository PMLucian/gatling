package waitingRoom.endpoints;

import static io.gatling.javaapi.http.HttpDsl.*;
import static waitingRoom.utils.Config.baseUrl;

import io.gatling.javaapi.http.HttpRequestActionBuilder;

public class WebEndpoints {
  public static final HttpRequestActionBuilder authorizeEndpoint =
      http("authorizeEndpoint")
          .get(
              baseUrl
                  + "/oauth2/authorize?redirect_uri=https%3A%2F%2Fstg.oneclub.golf%2Faccount%2Fsso%2Fcallback&response_type=code&client_id=6lctf8n08bcfm9bibveg3d23ct&identity_provider=COGNITO&scope=email+openid+profile+aws.cognito.signin.user.admin&state=tfpAMcuzAXKh1fJ8ar2UQD5eCFspjzCG&code_challenge=VxO-iAbULmk0RGXfHqXu_D86CosLAVwDYkk7lDYWn7c&code_challenge_method=S256")
          .disableFollowRedirect()
          .check(status().in(200, 302));
  //          .check(headerRegex("Set-Cookie", ""));
}
