package com.app.coronavirustracker.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
public class Location {

	/** The state. */
	private String state;

	/** The country. */
	private String country;

	/** The latest total cases. */
	private int latestTotalCases;

	/** The new cases. */
	private int newCases;

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

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
	 *            the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the latest total cases.
	 *
	 * @return the latest total cases
	 */
	public int getLatestTotalCases() {
		return latestTotalCases;
	}

	/**
	 * Sets the latest total cases.
	 *
	 * @param latestTotalCases
	 *            the new latest total cases
	 */
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}

	/**
	 * Gets the new cases.
	 *
	 * @return the new cases
	 */
	public int getNewCases() {
		return newCases;
	}

	/**
	 * Sets the new cases.
	 *
	 * @param newCases the new new cases
	 */
	public void setNewCases(int newCases) {
		this.newCases = newCases;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases + "]";
	}

}
