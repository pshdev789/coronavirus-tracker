package com.app.coronavirustracker.client;

import java.io.IOException;
import java.io.StringReader;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.app.coronavirustracker.util.CoronaVirusConstants;

@Component
public class CoronaTrackerOutBoundClientImpl implements CoronaTrackerOutboundClient {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment env;

	private static String baseUrl;

	@PostConstruct
	public void init() {
		this.baseUrl = env.getProperty(CoronaVirusConstants.BASE_URL);
	}

	
	public ResponseEntity<String> callOutBoundService(String url, HttpEntity<String> requestEntity) {

		return restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

	}

	@Override
	public Iterable<CSVRecord> getCSVRecords(String urlStr) {

		Iterable<CSVRecord> records = null;
		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			StringBuilder url = new StringBuilder();
			url.append(baseUrl);
			url.append(urlStr);
			ResponseEntity<String> response = callOutBoundService(url.toString(), requestEntity);
			String responseStr = response.getBody();
			StringReader csvBodyReader = new StringReader(responseStr);

			records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}
}
