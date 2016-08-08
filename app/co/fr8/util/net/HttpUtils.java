package co.fr8.util.net;

import co.fr8.data.interfaces.dto.Fr8HubSecurityDTO;
import co.fr8.play.ApplicationConstants;
import co.fr8.util.CollectionUtils;
import co.fr8.util.logging.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO: Implement
 */
public class HttpUtils {

  private static CloseableHttpClient client = HttpClients.createDefault();

  /**
   *
   * @param url
   * @param params
   * @return
   */
  public static String post(String url, Map<String, String> params) {
    List<NameValuePair> nameValuePairs = null;
    if (CollectionUtils.isNotEmpty(params)) {
      nameValuePairs = new ArrayList<>();

      for(Map.Entry<String, String> entry : params.entrySet()) {
        nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }
    }
    return sendEntityRequest(url, HttpPost.METHOD_NAME, nameValuePairs);
  }

  /**
   *
   * @param url
   * @param params
   * @return
   */
  public static String post(String url, List<NameValuePair> params) {
    return sendEntityRequest(url, HttpPost.METHOD_NAME, params);
  }

  /**
   *
   * @param url
   * @return
   */
  public static String get(String url) {

    if (StringUtils.isBlank(url)) {
      Logger.warn("Attempt to make GET request with no URL");
      return "Invalid request";
    }

    try {
      HttpGet getRequest = new HttpGet(url);
      CloseableHttpResponse response = client.execute(getRequest);

      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        // TODO: Implement
        Logger.debug("Successful response");
      } else {
        Logger.warn("Bad response: " + response.getStatusLine().getStatusCode()
            + ": " + response.getStatusLine().getReasonPhrase());
      }

      HttpEntity responseEntity = response.getEntity();
      OutputStream ostream = new ByteArrayOutputStream();
      responseEntity.writeTo(ostream);
      ostream.close();

      Logger.debug("Received GET response: " + ostream);
      EntityUtils.consume(responseEntity);
      return ostream.toString();

    } catch (IOException e) {
      Logger.error("Exception while processing GET response", e);
      return StringUtils.EMPTY;
    }
  }

  /**
   *
   * @param url
   * @return
   */
  public static String getSecure(String url, Fr8HubSecurityDTO hubSecurityDTO) {

    if (StringUtils.isBlank(url)) {
      Logger.warn("Attempt to make GET request with no URL");
      return "Invalid request";
    }

    try {
      HttpGet getRequest = new HttpGet(url);
      String headerAuthorization = "FR8-TOKEN key=" + hubSecurityDTO.getFr8HubCallBackSecret()
          + ", user=" + hubSecurityDTO.getFr8UserId();
      getRequest.setHeader("Authorization", headerAuthorization);
      CloseableHttpResponse response = client.execute(getRequest);

      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        // TODO: Implement
        Logger.debug("Successful response");
      } else {
        Logger.warn("Bad response: " + response.getStatusLine().getStatusCode()
            + ": " + response.getStatusLine().getReasonPhrase());
      }

      HttpEntity responseEntity = response.getEntity();
      OutputStream ostream = new ByteArrayOutputStream();
      responseEntity.writeTo(ostream);
      ostream.close();

      Logger.debug("Received GET response: " + ostream);
      EntityUtils.consume(responseEntity);
      return ostream.toString();

    } catch (IOException e) {
      Logger.error("Exception while processing GET response", e);
      return StringUtils.EMPTY;
    }
  }

  private String getAuthentication() {
    String url = ApplicationConstants.TERMINAL_HOST + "/authentication/internal";
    return get(url);
  }

  /**
   *
   * @param url
   * @param params
   * @return
   */
  public static String put(String url, List<NameValuePair> params) {
    return sendEntityRequest(url, HttpPut.METHOD_NAME, params);
  }

  public static String postJson(String url, JsonNode json) {
    StringEntity entity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
    entity.setContentType(MediaType.APPLICATION_JSON);
    return sendEntityRequest(url, HttpPost.METHOD_NAME, entity);
  }

  public static String putJson(String url, JsonNode json) {
    StringEntity entity = new StringEntity(json.toString(), StandardCharsets.UTF_8);
    entity.setContentType(MediaType.APPLICATION_JSON);
    return sendEntityRequest(url, HttpPut.METHOD_NAME, entity);
  }

  private static String sendEntityRequest(String url, String method, AbstractHttpEntity entity) {
    Logger.debug("Sending entity request of type " + method + " to " + url);

    try {
      HttpEntityEnclosingRequestBase request;
      switch (method) {
        case HttpPost.METHOD_NAME:
          request = new HttpPost(url);
          break;
        case HttpPut.METHOD_NAME:
          request = new HttpPut(url);
          break;
        default: {
          return "INVALID REQUEST: PLACEHOLDER";
        }
      }

      request.setEntity(entity);
      CloseableHttpResponse response = client.execute(request);

      try {


        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

        } else {

        }

        HttpEntity responseEntity = response.getEntity();

        OutputStream ostream = new ByteArrayOutputStream();
        responseEntity.writeTo(ostream);
        ostream.close();

        Logger.debug("Received response " + ostream);

        EntityUtils.consume(responseEntity);
        return ostream.toString();

      } finally {
        Logger.debug("Closing response");
        response.close();
      }

    } catch (IOException e) {
      Logger.error("Exception while processing post request to: " + url
          + " with params " + entity, e);
      return null;
    }

  }

  private static String sendEntityRequest(String url, String method,
                                          List<NameValuePair> params) {
    UrlEncodedFormEntity urlEntity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
    return sendEntityRequest(url, method, urlEntity);
  }

  /**
   * Helper method that parses a request URI with query parameters
   * @param requestString the request URI
   * @return a map of the parameters as keys with their values
   */
  public static Map<String, String> requestStringToMap(String requestString) {

    Logger.debug("Parsing requestString to map: " + requestString);

    Map<String, String> ret = null;

    if (StringUtils.isNotBlank(requestString)) {

      String[] requestParams = StringUtils.split(requestString, "?");

      if (requestParams.length > 1) {
        Logger.debug("Removing params from " + requestString);
        requestString = requestParams[1];
        Logger.debug("Updated requestString " + requestString);
      }

      String[] keyValuePairs = requestString.split("&");
      ret = new HashMap<>();

      if (CollectionUtils.isNotEmpty(keyValuePairs)) {
        for (String keyValuePair : keyValuePairs) {
          String[] params = keyValuePair.split("=");

          if (CollectionUtils.isNotEmpty(params) && params.length == 2) {
            Logger.debug("Adding param " + params[0] + " to map " + params[1]);
            ret.put(params[0], params[1]);
          }
        }
      }
    }

    Logger.debug("Returning map " + ret);

    return ret;
  }
}
