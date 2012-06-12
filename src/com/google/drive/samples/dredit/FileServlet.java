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
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.drive.samples.dredit.model.ClientFile;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet providing a small API for the DrEdit JavaScript client to use in
 * manipulating files.  Each operation (GET, POST, PUT) issues requests to the
 * Google Drive API.
 *
 * @author vicfryzel@google.com (Vic Fryzel)
 */
@SuppressWarnings("unused")
public class FileServlet extends DrEditServlet {
  
  private static final long serialVersionUID = 1L;

  /**
   * Given a {@code file_id} URI parameter, return a JSON representation
   * of the given file.
   */
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	sendError(resp, 501, "Not Implemented");
  }

  /**
   * Create a new file given a JSON representation, and return the JSON
   * representation of the created file.
   */
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	sendError(resp, 501, "Not Implemented");
  }

  /**
   * Update a file given a JSON representation, and return the JSON
   * representation of the created file.
   */
  @Override
  public void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	sendError(resp, 501, "Not Implemented");
  }

  /**
   * Download the content of the given file.
   *
   * @param service Drive service to use for downloading.
   * @param file File metadata object whose content to download.
   * @return String representation of file content.  String is returned here
   *         because this app is setup for text/plain files.
   * @throws IOException Thrown if the request fails for whatever reason.
   */
  private String downloadFileContent(Drive service, File file) throws IOException {
	return "";
  }

  /**
   * Build and return a Drive service object based on given request parameters.
   *
   * @param req Request to use to fetch code parameter or accessToken session
   *            attribute.
   * @param resp HTTP response to use for redirecting for authorization if
   *             needed.
   * @return Drive service object that is ready to make requests, or null if
   *         there was a problem.
   */
  private Drive getDriveService(HttpServletRequest req,
      HttpServletResponse resp) {
    Credential credentials = getCredential(req, resp);

    return Drive.builder(TRANSPORT, JSON_FACTORY)
        .setHttpRequestInitializer(credentials).build();
  }
}