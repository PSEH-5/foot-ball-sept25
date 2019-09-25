package com.football.footdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/standings")
public class FootBallController {
	
	@Value("${APIKey}")
	public String apiKey;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{leag_id}")
	public String getTeamStandings(@PathVariable("leag_id") Integer leag_id) {
		
		String uri = "https://apiv2.apifootball.com/?action=get_standings&league_id="+leag_id+"&APIkey=" + apiKey;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		
		ResponseEntity result= restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		
		return result.getBody().toString();
	}

}
