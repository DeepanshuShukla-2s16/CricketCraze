package com.cricket.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cricket.entities.Match;


public interface MatchServices {
//get lives matches
	List<Match> getLivesMatchs();

//	get all completed matches
	List<Match> getAllCompletedMatchs();
	
	
//get points table
	List<List<String>> getPointTable();
}
