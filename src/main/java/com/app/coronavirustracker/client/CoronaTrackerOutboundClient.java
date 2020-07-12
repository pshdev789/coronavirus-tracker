package com.app.coronavirustracker.client;

import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

/**
 * The Interface CoronaTrackerOutboundClient.
 */
public interface CoronaTrackerOutboundClient {
	
	/**
	 * Call out bound service.
	 *
	 * @param url the url
	 * @param requestEntity the request entity
	 * @return the response entity
	 */
	public ResponseEntity<String> callOutBoundService(String url, HttpEntity<String> requestEntity);
	
	public Iterable<CSVRecord> getCSVRecords(String url);

}
