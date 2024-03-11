package com.cricket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cricket.entities.Match;
import com.cricket.services.MatchServices;

@RestController
@RequestMapping("/cricket")
@CrossOrigin("*")
public class MatchController {


	private MatchServices matchServices;

	public MatchController(MatchServices matchServices) {
		this.matchServices = matchServices;
	}
	
	@GetMapping("/lives")
	public ResponseEntity<List<Match>> getAllLivesMatches(){
		return new ResponseEntity<>(this.matchServices.getLivesMatchs(), HttpStatus.OK);
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<Match>> getAllMAtches(){
		return new ResponseEntity<List<Match>>(this.matchServices.getAllCompletedMatchs(),HttpStatus.OK);
	}
	
	@GetMapping("/point-table")
    public ResponseEntity<?> getCWC2023PointTable() {
        return new ResponseEntity<>(this.matchServices.getPointTable(), HttpStatus.OK);
    }
	
}
