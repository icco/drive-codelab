/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.drive.samples.dredit;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.drive.samples.dredit.CredentialMediator.InvalidClientSecretsException;
import com.google.drive.samples.dredit.CredentialMediator.NoRefreshTokenException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract servlet that sets up credentials and provides some convenience
 * methods.
 *
 * @author vicfryzel@google.com (Vic Fryzel)
 */
@SuppressWarnings("unused")
public abstract class DrEditServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  protected static final HttpTransport TRANSPORT = new NetHttpTransport();
  protected static final JsonFactory JSON_FACTORY = new JacksonFactory();

  /**
   * Default MIME type of files created or handled by DrEdit.
   *
   * This is also set in the Google APIs Console under the Drive SDK tab.
   */
  public static final String DEFAULT_MIMETYPE = "text/plain";

  /**
   * MIME type to use when sending responses back to DrEdit JavaScript client.
   */
  public static final String JSON_MIMETYPE = "application/json";

  /**
   * Path component under war/ to locate client_secrets.json file.
   */
  public static final String CLIENT_SECRETS_FILE_PATH
      = "/WEB-INF/client_secrets.json";

  /**
   * Scopes for which to request access from the user.
   */
  public static final List<String> SCOPES = Arrays.asList(
      // Required to access and manipulate files.
      "https://www.googleapis.com/auth/drive.file",
      // Required to identify the user in our data store.
      "https://www.googleapis.com/auth/userinfo.email",
      "https://www.googleapis.com/auth/userinfo.profile");

  protected InputStream getClientSecretsStream() {
	return null;
  }

  protected CredentialMediator getCredentialMediator(
      HttpServletRequest req, HttpServletResponse resp) {
    // Authorize or fetch credentials.  Required here to ensure this happens
    // on first page load.  Then, credentials will be stored in the user's
    // session.
    CredentialMediator mediator = new CredentialMediator(req, getClientSecretsStream(), SCOPES);
    return mediator;
  }

  protected Credential getCredential(HttpServletRequest req, HttpServletResponse resp) {
	return new GoogleCredential.Builder().build();
  }

  protected String getClientId(HttpServletRequest req, HttpServletResponse resp) {
	return "";
  }

  protected void deleteCredential(HttpServletRequest req, HttpServletResponse resp) {
  }
  
  protected void sendError(HttpServletResponse resp, int code, String message) {
    try {
      resp.sendError(code, message);
    } catch (IOException e) {
      throw new RuntimeException(message);
    }
  }
}