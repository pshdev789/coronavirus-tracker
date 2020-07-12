package com.app.coronavirustracker.services;

import java.util.List;

import com.app.coronavirustracker.model.CaseByCountry;

/**
 * The Interface CoronaVirusService.
 */
public interface CoronaVirusService {

	/**
	 * Gets the virus data.
	 *
	 * @return the virus data
	 */
	public void getVirusData();
	
	/**
	 * Gets the cases by country.
	 *
	 * @return the cases by country
	 */
	public List<CaseByCountry> getCasesByCountry();
	
}
