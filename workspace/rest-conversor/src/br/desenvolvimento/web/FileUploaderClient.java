package br.desenvolvimento.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;


public class FileUploaderClient {
	
	public static void main(String[] args) {
		
		
		File inFile = new File("C:\\Users\\Luis\\Arquivo\\Arquivo.mkv");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inFile);
			DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
			
			
			HttpPost httppost = new HttpPost("http://localhost:8080/FileUploaderRESTService-1/rest/upload");
			MultipartEntity entity = new MultipartEntity();
			
			entity.addPart("file", new InputStreamBody(fis, inFile.getName()));
			httppost.setEntity(entity);
			
			HttpResponse response = httpclient.execute(httppost);
			
			int statusCode = response.getStatusLine().getStatusCode();
			HttpEntity responseEntity = response.getEntity();
			String responseString = EntityUtils.toString(responseEntity, "UTF-8");
			
			System.out.println("[" + statusCode + "] " + responseString);
			
		} catch (ClientProtocolException e) {
			System.err.println("Unable to make connection");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Unable to read file");
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) fis.close();
			} catch (IOException e) {}
		}
	}
	
}