/* Copyright IBM Corp. 2014
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cognitive.personality;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;


@MultipartConfig
public class DemoServlet extends HttpServlet 
{
	private static Logger logger = Logger.getLogger(DemoServlet.class.getName());
	private static final long serialVersionUID = 1L;

	private String serviceName = "personality_insights";
	private String mobydickcp1;
	// If running locally complete the variables below
	// with the information in VCAP_SERVICES
	private String baseURL = "https://gateway.watsonplatform.net/personality-insights/api";
	private String username = "2649831b-3b65-467c-9bd5-1300f0775cdc";
	private String password = "ESpPTxWTDi4t";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("content", getDefaultText());
		req.getRequestDispatcher("/pi/index.jsp").forward(req, resp);
	}

	/**
	 * Create and POST a request to the Personality Insights service
	 * 
	 * @param req
	 *            the Http Servlet request
	 * @param resp
	 *            the Http Servlet pesponse
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("doPost");

		req.setCharacterEncoding("UTF-8");
		// create the request
		String text = req.getParameter("text");

		try {
			URI profileURI = new URI(baseURL + "/v2/profile").normalize();

			Request profileRequest = Request.Post(profileURI)
					.addHeader("Accept", "application/json")
					.bodyString(text, ContentType.TEXT_PLAIN);

			Executor executor = Executor.newInstance().auth(username, password);
			Response response = executor.execute(profileRequest);
			HttpResponse httpResponse = response.returnResponse();
			resp.setStatus(httpResponse.getStatusLine().getStatusCode());

			ServletOutputStream servletOutputStream = resp.getOutputStream();
			httpResponse.getEntity().writeTo(servletOutputStream);
			servletOutputStream.flush();
			servletOutputStream.close();

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Service error: " + e.getMessage(), e);
			resp.setStatus(HttpStatus.SC_BAD_GATEWAY);
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
		processVCAPServices();
	}

	/**
	 * If exists, process the VCAP_SERVICES environment variable in order to get
	 * the username, password and baseURL
	 */
	private void processVCAPServices() {
		logger.info("Processing VCAP_SERVICES");
		JSONObject sysEnv = getVCAPServices();
		if (sysEnv == null)
			return;
		logger.info("Looking for: " + serviceName);

		for (Object key : sysEnv.keySet()) {
			String keyString = (String) key;
			logger.info("found key: " + key);
			if (keyString.startsWith(serviceName)) {
				JSONArray services = (JSONArray) sysEnv.get(keyString);
				JSONObject service = (JSONObject) services.get(0);
				JSONObject credentials = (JSONObject) service
						.get("credentials");
				baseURL = (String) credentials.get("url");
				username = (String) credentials.get("username");
				password = (String) credentials.get("password");
				logger.info("baseURL  = " + baseURL);
				logger.info("username = " + username);
				logger.info("password = " + password);
			} else {
				logger.info("Doesn't match /^" + serviceName + "/");
			}
		}
	}

	/**
	 * Gets the <b>VCAP_SERVICES</b> environment variable and return it as a
	 * JSONObject.
	 * 
	 * @return the VCAP_SERVICES as Json
	 */
	private JSONObject getVCAPServices() {
		String envServices = System.getenv("VCAP_SERVICES");
		if (envServices == null)
			return null;
		JSONObject sysEnv = null;
//		try {
			sysEnv = new JSONObject(envServices);
//		} catch (IOException e) {
//			// Do nothing, fall through to defaults
//			logger.log(Level.SEVERE,
//					"Error parsing VCAP_SERVICES: " + e.getMessage(), e);
//		}
		return sysEnv;
	}

	private String getDefaultText() {
		if (mobydickcp1 == null) {
			byte[] encoded;
//			try {
//				Path path = Paths.get(this.getClass().getResource("mobydick.txt").toURI());
//				encoded = Files.readAllBytes(path);
//				mobydickcp1 =  new String(encoded, StandardCharsets.UTF_8);
//				System.out.println(mobydickcp1);
//			} catch (Exception e) {
//				logger.log(Level.SEVERE, "mobidick.txt file not found: " + e.getMessage(), e);
//			}
			try {
				//URI uri = this.getClass().getResource("mobydick.txt").toURI();
				//String fileName = uri.getPath();
				//System.out.println(getClass()+" "+fileName);
				InputStream is = getServletContext().getResourceAsStream("/pi/mobydick.txt");
				System.out.println(getClass()+" is="+is);
				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[16384];
				while ((nRead = is.read(data, 0, data.length)) != -1) {
				  buffer.write(data, 0, nRead);
				}
				buffer.flush();
				encoded =  buffer.toByteArray();
//				RandomAccessFile f = new RandomAccessFile(fileName, "r");
//				encoded = new byte[(int)f.length()];
//				f.read(encoded);
//				f.close();
			    mobydickcp1 =  new String(encoded, "UTF8");
				System.out.println(mobydickcp1);
			} catch (Exception e) {
				logger.log(Level.SEVERE, "mobidick.txt could not be read: " + e.getMessage(), e);
			}
		}
		return mobydickcp1;
	}
}
