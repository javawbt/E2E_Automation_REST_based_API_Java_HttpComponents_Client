package OnboardCloudAccount;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



//6.  Setup profiles and templates
//#Export Profiles
 
public class _6SetupProfilesAndTemplates {

	 
	
	@Test
	public void ExportDownloadProfiles() throws ClientProtocolException, IOException {

		Executor executor = Executor.newInstance();

		System.out.println(executor.execute(Request.Post("http://10.10.8.32:8080/AppCenter/services/rest"
				+ "/login").useExpectContinue().version(HttpVersion.HTTP_1_1)
				.bodyString("{\"username\":\"admin\","
						+ "\"password\":\"admin\"}", ContentType.APPLICATION_JSON))
						.returnResponse().toString());

		executor.execute(Request.Post("http://10.10.8.32:8080/AppCenter/services/rest/standards/fetch")
				.useExpectContinue()
				.version(HttpVersion.HTTP_1_1)
				.bodyString("{\"Name\":\"OS Profile\", " 
				+ "\"Criteria\": {\"ProfileID\":\"UBUNTU\"}}", ContentType.APPLICATION_JSON))
				.saveContent(new File("E:/newfile1.txt"));

	}
}
