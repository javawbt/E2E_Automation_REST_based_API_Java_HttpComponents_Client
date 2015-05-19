package OnboardCloudAccount;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//5.  Discover
//#Discover All
public class _5DiscoverAll {
	HttpClient client;
	HttpPost httpPost;
	HttpPut httpPut;
	HttpResponse response;
	HttpGet httpGet;
	HttpEntity httpEntity;
	JSONParser parser;
	InputStream inputStream;
	Object obj;
	JSONObject jsonObject;
	String actualStatus;
	String actualErrorCode;
	String errorMessage;
	String success;
	String failure;
	String filePath = "E:/LogFile.txt";
	String thisMethodName;
	@Before
	public void beforeEachIdividualTest() throws ClientProtocolException, IOException {
		thisMethodName = "beforeEachIdividualTest";
		client = HttpClients.createDefault();

		httpPost = new HttpPost("http://10.10.8.32:8080/AppCenter/services/rest/login");
		String json = "{\"username\":\"xxxxxx\",\"password\":\"xxxxxx\"}";
		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader("Content-type", "application/json");
		response = client.execute(httpPost);
	}

	@After
	public void afterEachIdividualTest() throws ClientProtocolException, IOException, org.json.simple.parser.ParseException {
		httpEntity = response.getEntity();
		InputStream is = httpEntity.getContent();
		String logFilePath = "E:/LogFile.txt";
		FileOutputStream fos = new FileOutputStream(new File(logFilePath));
		int inByte;
		while ((inByte = is.read()) != -1)
			fos.write(inByte);
		// is.close();
		// fos.close();

		parser = new JSONParser();

		obj = parser.parse(new FileReader(logFilePath));

		jsonObject = (JSONObject) obj;

		actualStatus = (String) jsonObject.get("status");
		actualErrorCode = (String) jsonObject.get("errorCode");
		errorMessage = (String) jsonObject.get("errorMessage");
		// String data = (String) jsonObject.get("data");
		success = "success";
		failure = "failure";
		System.out.println("\t\t\t" + thisMethodName + "");
		System.out.println("Status:		" + actualStatus);
		System.out.println("Error Code:	" + actualErrorCode);

		assertEquals(success, actualStatus);
		// Assert.assertTrue("status".equals("success"));
		// Assert.assertTrue(success.equals(actualStatus));
	}

	@Test
	public void GetDiscoverAll() throws ClientProtocolException, IOException, org.json.simple.parser.ParseException {
		thisMethodName = "GetDiscoverAll";
		httpGet = new HttpGet("http://10.10.8.32:8080/AppCenter/services/rest/cloudaccount/OpenStack%20Cloud%20Account/discoverAll");
		response = client.execute(httpGet);
		 
	}
}
