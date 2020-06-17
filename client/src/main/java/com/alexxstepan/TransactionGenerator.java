package com.alexxstepan;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TransactionGenerator implements Runnable {

	private Account account;
	private Gson gson;
	private CloseableHttpClient httpClient;

	public TransactionGenerator(Account account) {
		this.account = account;
		gson = new Gson();
		httpClient = HttpClients.custom().setMaxConnPerRoute(10).build();
	}

	@Override
	public void run() {
		String bookingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		try {
			int summary = 0;
			for (int i = 0; i < 100; i++) {
				Thread.sleep(1000);
				summary++;
				HttpPost post = new HttpPost("http://localhost:8080/api/transaction");

				Transaction transaction = new Transaction(account, "category " + i, bookingDate, i);
				StringEntity stringEntity = new StringEntity(gson.toJson(transaction));
				post.setEntity(stringEntity);
				post.setHeader("Accept", "application/json");
				post.setHeader("Content-type", "application/json");

				CloseableHttpResponse response = httpClient.execute(post);
				transaction = gson.fromJson(new JsonReader(new InputStreamReader(response.getEntity().getContent())), Transaction.class);
			}
			System.out.println("Summary should be " + summary);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
