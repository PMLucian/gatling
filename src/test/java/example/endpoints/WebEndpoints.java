package example.endpoints;

import static example.utils.Config.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.http.HttpRequestActionBuilder;

public class WebEndpoints {

  // Define the home page request with response status validation
  // Reference:
  // https://docs.gatling.io/reference/script/protocols/http/request/#checks
  public static final HttpRequestActionBuilder homePage =
      http("HomePage")
          .get(pageUrl)
          .check(status().in(200, 304)); // Accept both OK (200) and Not Modified (304) statuses

  // Define the login page request with response status validation
  // Reference:
  // https://docs.gatling.io/reference/script/protocols/http/request/#checks
  public static final HttpRequestActionBuilder loginPage =
      http("LoginPage").get(pageUrl + "/login").check(status().in(200, 304));

  public static final HttpRequestActionBuilder authorizeEndpoint =
      http("authorizeEndpoint")
          .get(
              pageUrl
                  + "/oauth2/authorize?redirect_uri=https%3A%2F%2Fstg.oneclub.golf%2Faccount%2Fsso%2Fcallback&response_type=code&client_id=6lctf8n08bcfm9bibveg3d23ct&identity_provider=COGNITO&scope=email+openid+profile+aws.cognito.signin.user.admin&state=tfpAMcuzAXKh1fJ8ar2UQD5eCFspjzCG&code_challenge=VxO-iAbULmk0RGXfHqXu_D86CosLAVwDYkk7lDYWn7c&code_challenge_method=S256")
          .disableFollowRedirect()
          .check(status().in(200, 302))
//          .check(headerRegex("Set-Cookie", ""));
}
