package com.app.coronavirustracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.coronavirustracker.model.CaseByCountry;
import com.app.coronavirustracker.services.CoronaVirusServiceImpl;
import com.app.coronavirustracker.util.CoronaVirusConstants;

/**
 * The Class CoronaTrackerController.
 */
@Controller
public class CoronaTrackerController {

	/** The corona service. */
	@Autowired
	CoronaVirusServiceImpl coronaService;

	/**
	 * Render home page.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@GetMapping("/")
	public String renderHomePage(Model model) {

		try {

			List<CaseByCountry> allCases = coronaService.getCasesByCountry();

			int totalReportedCases = allCases.stream().mapToInt(dt -> dt.getConfirmedCases()).sum();
			int totalNewCases = allCases.stream().mapToInt(dt -> dt.getNewCases()).sum();

			int totalRecoveredCases = allCases.stream().mapToInt(dt -> dt.getRecoveredCases()).sum();
			int totalNewRecovered = allCases.stream().mapToInt(dt -> dt.getNewRecovered()).sum();

			int totalDeathCases = allCases.stream().mapToInt(dt -> dt.getDeathCases()).sum();
			int totalNewDeaths = allCases.stream().mapToInt(dt -> dt.getNewDeaths()).sum();

			CaseByCountry sriLankaData = allCases.stream().filter(cc -> cc.getCountry().equalsIgnoreCase("Sri Lanka"))
					.collect(Collectors.toList()).get(0);

			int totalReportedSL = sriLankaData.getConfirmedCases();
			int totalNewSL = sriLankaData.getNewCases();

			int totalRecoveredSL = sriLankaData.getRecoveredCases();
			int totalNewRecoveredSL = sriLankaData.getNewRecovered();

			int totalDeathCasesSL = sriLankaData.getDeathCases();
			int totalNewDeathsSL = sriLankaData.getNewDeaths();

			// totalReportedCases
			model.addAttribute(CoronaVirusConstants.TOTAL_REPORTED_CASES_STR, totalReportedCases);
			model.addAttribute(CoronaVirusConstants.TOTAL_NEW_CASES_STR, totalNewCases);

			model.addAttribute(CoronaVirusConstants.TOTAL_RECOVERED_CASES_STR, totalRecoveredCases);
			model.addAttribute(CoronaVirusConstants.TOTAL_NEW_RECOVERED_STR, totalNewRecovered);

			model.addAttribute(CoronaVirusConstants.TOTAL_DEATH_CASES_STR, totalDeathCases);
			model.addAttribute(CoronaVirusConstants.TOTAL_NEW_DEATH_STR, totalNewDeaths);

			model.addAttribute(CoronaVirusConstants.TOTAL_REPORTED_SL_STR, totalReportedSL);
			model.addAttribute(CoronaVirusConstants.TOTAL_NEW_SL_STR, totalNewSL);

			model.addAttribute(CoronaVirusConstants.TOTAL_RECOVERED_SL_STR, totalRecoveredSL);
			model.addAttribute(CoronaVirusConstants.TOTAL_NEW_RECOVERED_SL_STR, totalNewRecoveredSL);

			model.addAttribute(CoronaVirusConstants.TOTAL_DEATH_SL_STR, totalDeathCasesSL);
			model.addAttribute(CoronaVirusConstants.TOTAL_NEW_DEATH_SL_STR, totalNewDeathsSL);

			model.addAttribute(CoronaVirusConstants.ALL_CASES, allCases);
			return "homePage";
		} catch (Exception e) {
			return "errorPage";
		}
	}
}
