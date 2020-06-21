package com.alexxstepan;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

	private Gson gson;
	private CloseableHttpClient httpClient;

	public Client() {
		gson = new Gson();
		httpClient = HttpClients.custom().setMaxConnPerRoute(10).build();
	}

	public static void main(String[] args) throws IOException {
		Client client = new Client();
		Account account = client.createAccount();

		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.submit(new TransactionGenerator(account));
		}
	}

	private Account createAccount() throws IOException {
		Account account = new Account(1L, "owner1", "USD", 0);

		HttpPost post = new HttpPost("http://localhost:8080/api/account");
		StringEntity stringEntity = new StringEntity(gson.toJson(account));
		post.setEntity(stringEntity);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");

		CloseableHttpResponse response = httpClient.execute(post);
		account = gson.fromJson(new JsonReader (new InputStreamReader(response.getEntity().getContent())), Account.class);
		return account;
	}
}
