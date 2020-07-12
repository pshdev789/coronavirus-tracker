package com.app.coronavirustracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.coronavirustracker.model.Location;
import com.app.coronavirustracker.services.CoronaVirusService;

@Controller
public class CoronaTrackerController {

	@Autowired
	CoronaVirusService coronaService;
	
	@GetMapping("/")
	public String renderHomePage(Model model) {
		
		List<Location> getData = coronaService.getAllLocations();
		int totalReportedCases = getData.stream().mapToInt(dt->dt.getLatestTotalCases()).sum();
		int totalNewCases= getData.stream().mapToInt(dt->dt.getNewCases()).sum();
		model.addAttribute("locationData",getData);
//		totalReportedCases
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);
	
		return "homePage";
	}
}
