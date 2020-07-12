package com.app.coronavirustracker.services;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.coronavirustracker.model.Location;
import com.app.coronavirustracker.util.CoronaVirusConstants;

@Service
public class CoronaVirusService {

	@Autowired
	private Environment env;
	@Autowired
	private RestTemplate restTemplate;

	/** logger declaration. */
	private static Logger logger = LoggerFactory.getLogger(CoronaVirusService.class);

	private List<Location> allLocations = new ArrayList<>();

	public List<Location> getAllLocations() {
		return allLocations;
	}

	// Execute this when the application starts
	@PostConstruct
	// Need to run this on a daily basis
	@Scheduled(cron = "* * 1 * * *")
	public void getVirusData() {
		// Concurrency reasons- if someone are trying to access to all loc lists, if in
		// a second this
		// newData is gonna retrieve
		List<Location> newStates = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		StringBuilder url = new StringBuilder();

		String responseStr = null;
		try {

			url.append(env.getProperty(CoronaVirusConstants.VIRUS_DATA_URL));
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);

			ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, requestEntity,
					String.class);
			responseStr = response.getBody();
			StringReader csvBodyReader = new StringReader(responseStr);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvBodyReader);
			for (CSVRecord record : records) {

				Location location = new Location();

				String state = record.get("Province/State");
				location.setState(state);

				String country = record.get("Country/Region");
				location.setCountry(country);

//				get the latest value of the todays column
				int lastColIndex= record.size() - 1;
				
				String lastColumnValue = record.get(lastColIndex);
				String prevColumnValue=record.get(lastColIndex-1);
				
				int todayCases = Integer.parseInt(lastColumnValue);
				int prevDayCases=Integer.parseInt(prevColumnValue);
				int newCases=todayCases-prevDayCases;
				location.setNewCases(newCases);
				location.setLatestTotalCases(todayCases);

				newStates.add(location);
				
//				logger.info("Location Obj: {}",location.toString());
			}
			this.allLocations = newStates;

		} catch (Exception e) {

//			logger.error("Exception occured : {}. Response: {}", e.getCause(), responseStr);
		}

	}
}
