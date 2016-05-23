package github;

import co.fr8.data.interfaces.dto.AuthorizationTokenDTO;
import co.fr8.data.interfaces.dto.ExternalAuthDTO;
import co.fr8.util.json.JsonUtils;
import co.fr8.util.logging.Logger;
import co.fr8.util.net.HttpUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.fr8.play.ApplicationConstants.*;

/**
 * TODO: Implement
 */
public class Authentication {

  private final String gitHubAuthDomain;
  private final String gitHubAppKey;
  private final String gitHubAppSecret;
  private final String gitRedirectUri;

  public Authentication() {
    this.gitHubAuthDomain = AUTH_DOMAIN;
    this.gitHubAppKey = AUTH_APP_KEY;
    this.gitHubAppSecret = CLIENT_SECRET;
    this.gitRedirectUri = AUTH_PATH;
  }

  public AuthorizationTokenDTO authenticate(ExternalAuthDTO externalAuthDTO) {
    AuthorizationTokenDTO authTokenDTO = new AuthorizationTokenDTO();

    if (StringUtils.isBlank(AUTH_TOKEN_PATH)) {
      authTokenDTO.setError("Authorization token path is not configured, no request can be sent");
      Logger.warn(authTokenDTO.getError());
    } else {
      Map<String, String> requestParamMap =
          HttpUtils.requestStringToMap(externalAuthDTO.getRequestQueryString());

      String code = requestParamMap.get(OAUTH_CODE_PARAM);
      String state = requestParamMap.get(OAUTH_STATE_PARAM);

      List<NameValuePair> nameValuePairList = new ArrayList<>();
      nameValuePairList.add(new BasicNameValuePair(OAUTH_CODE_PARAM, code));
      nameValuePairList.add(new BasicNameValuePair(OAUTH_STATE_PARAM, state));
      nameValuePairList.add(new BasicNameValuePair("client_id", CLIENT_ID));
      nameValuePairList.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
      nameValuePairList.add(new BasicNameValuePair("redirect_uri", AUTH_PATH));

      Logger.debug("Calling post for auth token with url: " + AUTH_TOKEN_PATH +
          " and parameters: code " + code + ", state: " + state);

      String response = HttpUtils.post(AUTH_TOKEN_PATH, nameValuePairList);

      // TODO: Process the response in order to get the auth_token parameter
      // https://developer.github.com/v3/oauth/
      Map<String, String> responseMap = HttpUtils.requestStringToMap(response);

      String accessToken = responseMap.get("access_token");
      authTokenDTO.setExternalStateToken(state);
//      authTokenDTO.setUserId(externalAuthDTO.getFr8UserId());
//      authTokenDTO.setTerminalId("0");
      authTokenDTO.setExternalAccountId(getCurrentGitHubUserId(accessToken));
      if (StringUtils.isNotBlank(accessToken)) {
        authTokenDTO.setToken(accessToken);

      } else {
        authTokenDTO.setError("Authorization request did not return a valid token, see the terminal log file for more information");
      }
    }

    return authTokenDTO;
  }

  private String getCurrentGitHubUserId(String accessToken) {

    String response =
        HttpUtils.get("https://api.github.com/user?access_token=" + accessToken);

    JsonNode userResponse = JsonUtils.writeStringToObject(response);

    Logger.debug("Request for current GitHub user returns: " + userResponse);

    return (userResponse == null || userResponse.get("id") == null) ?
        StringUtils.EMPTY : userResponse.get("id").toString();
  }
/*

  public ExternalAuthUrlDTO getExternalAuthUrl() {
    return new ExternalAuthUrlDTO();
  }

  private String createAuthUrl(String externalStateValue) {

  }
*/


  public String getGitRedirectUri() {
    return gitRedirectUri;
  }

  public String getGitHubAuthDomain() {
    return gitHubAuthDomain;
  }

  public String getGitHubAppKey() {
    return gitHubAppKey;
  }

  public String getGitHubAppSecret() {
    return gitHubAppSecret;
  }
}
