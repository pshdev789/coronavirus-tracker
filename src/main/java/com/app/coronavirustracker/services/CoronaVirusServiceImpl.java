package com.app.coronavirustracker.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.app.coronavirustracker.client.CoronaTrackerOutboundClient;
import com.app.coronavirustracker.model.CaseByCountry;
import com.app.coronavirustracker.util.CoronaVirusConstants;

/**
 * The Class CoronaVirusService.
 */
@Service
public class CoronaVirusServiceImpl implements CoronaVirusService {

	/** The env. */
	@Autowired
	private Environment env;

	/** The client. */
	@Autowired
	private CoronaTrackerOutboundClient client;

	/** logger declaration. */
	private static Logger logger = LoggerFactory.getLogger(CoronaVirusServiceImpl.class);

	private List<CaseByCountry> casesByCountry = new ArrayList<>();

	/**
	 * @return the casesByCountry
	 */
	public List<CaseByCountry> getCasesByCountry() {

		return casesByCountry;
	}

	/**
	 * Gets the virus data. @Scheduled-for concurrency access,while updating to the
	 * latest when a user trying to access
	 * 
	 * @return the virus data
	 */
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void getVirusData() {

		String url = env.getProperty(CoronaVirusConstants.VIRUS_DATA_URL);
		getConfirmedStatus(url);

		url = env.getProperty(CoronaVirusConstants.DEATH_DATA_URL);
		getNewDeathStatus(url);

		url = env.getProperty(CoronaVirusConstants.RECOV_DATA_URL);
		getNewRecoveredStatus(url);
	

	}

	/**
	 * Gets the confirmed status.
	 *
	 * @param url
	 *            the url
	 * @return the confirmed status
	 */
	// @Scheduled(cron = "* * 1 * * *")
	private void getConfirmedStatus(String url) {
		try {

			Iterable<CSVRecord> records = client.getCSVRecords(url);
			for (CSVRecord record : records) {

				CaseByCountry countryCase = new CaseByCountry();
				String country = record.get("Country/Region");

				// get the latest value of the todays column
				int lastColIndex = record.size() - 1;

				String lastColumnValue = record.get(lastColIndex);
				String prevColumnValue = record.get(lastColIndex - 1);

				int uptodayCases = Integer.parseInt(lastColumnValue);
				int prevDayCases = Integer.parseInt(prevColumnValue);
				int newCases = uptodayCases - prevDayCases;

				countryCase.setCountry(country);
				countryCase.setConfirmedCases(uptodayCases);
				countryCase.setNewCases(newCases);
				casesByCountry.add(countryCase);

			}

		} catch (Exception e) {

			logger.error("Exception occured : {}.", e.getMessage());
		}

	}

	/**
	 * Gets the new death status.
	 *
	 * @param url
	 *            the url
	 * @return the new death status
	 */
	// @Scheduled(cron = "* * 1 * * *")
	private void getNewDeathStatus(String url) {

		try {

			Iterable<CSVRecord> records = client.getCSVRecords(url);
			for (CSVRecord record : records) {

				String country = record.get("Country/Region");

				// get the latest value of the todays column
				int lastColIndex = record.size() - 1;

				String lastColumnValue = record.get(lastColIndex);
				String prevColumnValue = record.get(lastColIndex - 1);

				int uptodayCases = Integer.parseInt(lastColumnValue);
				int prevDayCases = Integer.parseInt(prevColumnValue);
				int newCases = uptodayCases - prevDayCases;

				casesByCountry.stream().forEach(coun -> {
					if (country.equals(coun.getCountry())) {
						coun.setDeathCases(uptodayCases);
						coun.setNewDeaths(newCases);

					}

				});
			}

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage());
		}

	}

	/**
	 * Gets the new recovered status.
	 *
	 * @param url
	 *            the url
	 * @return the new recovered status
	 */
	// @Scheduled(cron = "* * 1 * * *")
	private void getNewRecoveredStatus(String url) {
		try {
			Iterable<CSVRecord> records = client.getCSVRecords(url);
			for (CSVRecord record : records) {

				String country = record.get("Country/Region");

				// get the latest value of the todays column
				int lastColIndex = record.size() - 1;

				String lastColumnValue = record.get(lastColIndex);
				String prevColumnValue = record.get(lastColIndex - 1);

				int uptodayCases = Integer.parseInt(lastColumnValue);
				int prevDayCases = Integer.parseInt(prevColumnValue);
				int newCases = uptodayCases - prevDayCases;

				casesByCountry.stream().forEach(coun -> {
					if (country.equals(coun.getCountry())) {
						coun.setRecoveredCases(uptodayCases);
						coun.setNewRecovered(newCases);

					}

				});

			}

		} catch (Exception e) {

			logger.error("Exception occured : {}.", e.getMessage());
		}

	}

}
