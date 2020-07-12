package com.app.coronavirustracker.model;

// TODO: Auto-generated Javadoc
/**
 * The Class CaseByCountry.
 */
public class CaseByCountry {

	/** The country. */
	private String country;

	/** The confirmed cases. */
	private int confirmedCases;

	/** The new cases. */
	private int newCases;

	/** The recovered cases. */
	private int recoveredCases;

	/** The new recovered. */
	private int newRecovered;

	/** The death cases. */
	private int deathCases;

	/** The new deaths. */
	private int newDeaths;

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the confirmed cases.
	 *
	 * @return the confirmedCases
	 */
	public int getConfirmedCases() {
		return confirmedCases;
	}

	/**
	 * Sets the confirmed cases.
	 *
	 * @param confirmedCases
	 *            the confirmedCases to set
	 */
	public void setConfirmedCases(int confirmedCases) {
		this.confirmedCases = confirmedCases;
	}

	/**
	 * Gets the new cases.
	 *
	 * @return the newCases
	 */
	public int getNewCases() {
		return newCases;
	}

	/**
	 * Sets the new cases.
	 *
	 * @param newCases
	 *            the newCases to set
	 */
	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}

	/**
	 * Gets the recovered cases.
	 *
	 * @return the recoveredCases
	 */
	public int getRecoveredCases() {
		return recoveredCases;
	}

	/**
	 * Sets the recovered cases.
	 *
	 * @param recoveredCases
	 *            the recoveredCases to set
	 */
	public void setRecoveredCases(int recoveredCases) {
		this.recoveredCases = recoveredCases;
	}

	/**
	 * Gets the new recovered.
	 *
	 * @return the newRecovered
	 */
	public int getNewRecovered() {
		return newRecovered;
	}

	/**
	 * Sets the new recovered.
	 *
	 * @param newRecovered
	 *            the newRecovered to set
	 */
	public void setNewRecovered(int newRecovered) {
		this.newRecovered = newRecovered;
	}

	/**
	 * Gets the death cases.
	 *
	 * @return the deathCases
	 */
	public int getDeathCases() {
		return deathCases;
	}

	/**
	 * Sets the death cases.
	 *
	 * @param deathCases
	 *            the deathCases to set
	 */
	public void setDeathCases(int deathCases) {
		this.deathCases = deathCases;
	}

	/**
	 * Gets the new deaths.
	 *
	 * @return the newDeaths
	 */
	public int getNewDeaths() {
		return newDeaths;
	}

	/**
	 * Sets the new deaths.
	 *
	 * @param newDeaths
	 *            the newDeaths to set
	 */
	public void setNewDeaths(int newDeaths) {
		this.newDeaths = newDeaths;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CaseByCountry [country=" + country + ", confirmedCases=" + confirmedCases + ", newCases=" + newCases
				+ ", recoveredCases=" + recoveredCases + ", newRecovered=" + newRecovered + ", deathCases=" + deathCases
				+ ", newDeaths=" + newDeaths + "]";
	}

}
