package com.cricket.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cricket.entities.Match;
import com.cricket.repositories.MatchRepository;
import com.cricket.services.MatchServices;

@Service
public class MatchServicesImpl implements MatchServices{
	


	
//	history
	@Override
	public List<Match> getAllCompletedMatchs() {
		List<Match> recentMatches = new ArrayList<>();
		
		try {
			
			String url = "https://www.cricbuzz.com/cricket-match/live-scores/recent-matches";
			Document document = Jsoup.connect(url).get();
			Elements recentMatchElements = document.select("div.cb-mtch-lst.cb-col.cb-col-100.cb-tms-itm>div.cb-col-100.cb-col.cb-schdl");
//			Elements recentMatchElements = document.select("div.cb-mtch-lst.cb-col.cb-col-100.cb-tms-itm");
			System.out.println(recentMatchElements.size());
//			for(Element recentMatch : recentMatchElements) {
//				System.out.println("recent match Details : "+recentMatch.text());
//			}
			
			
			

			
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}

		return recentMatches;
	}

	
//	live matches
	@Override
	public List<Match> getLivesMatchs() {
		List<Match> matches = new ArrayList<>();
        try {
        	
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element liveMatch : liveScoreElements) {
                String teamsHeading = liveMatch.select("h3.cb-lv-scr-mtch-hdr>a").text();
                String matchNumberVenue = liveMatch.select("span").text();
                
                Elements matchBatTeamInfo = liveMatch.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                
                Elements bowlTeamInfo = liveMatch.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                
                String textLive = liveMatch.select("div.cb-text-live").text();
                String textComplete = liveMatch.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = liveMatch.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match match = new Match();
                match.setTeamHeading(teamsHeading);
                match.setMatchNumberVenue(matchNumberVenue);
                match.setBattingTeam(battingTeam);
                match.setBattingTeamScore(score);
                match.setBowlTeam(bowlTeam);
                match.setBowlTeamScore(bowlTeamScore);
                match.setLiveText(textLive);
                match.setMatchLink(matchLink);
                match.setTextComplete(textComplete);
               
                matches.add(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
//        return all live matches
        return matches;
	}




	
	@Override
    public List<List<String>> getPointTable() {
        List<List<String>> pointTable = new ArrayList<>();
        String tableURL = "https://www.cricbuzz.com/cricket-stats/points-table/test/icc-world-test-championship";
        
        
       
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements pntTblCompnent= document.select("div.cb-col-67.cb-col.cb-left.cb-hm-rght");
            String pntTblHeading = pntTblCompnent.select("h2.cb-mat-mnu-wrp.cb-min-pad").text();
            List<String> heading = new ArrayList<>();
            heading.add(pntTblHeading);
            pointTable.add(heading);
            
            Elements table = pntTblCompnent.select("table.cb-srs-pnts");   
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
         
            
            Elements tableBodys = table.select("tbody>*");
            
            tableBodys.forEach(tblRows -> {
            	List<String> pointRow = new ArrayList<>();
            	Elements tblRow = tblRows.select("tr>.cb-srs-pnts-td");

            	System.out.println("=================row================");
            	
            	for(Element td : tblRow) {
            		String cell = td.text();
            		pointRow.add(cell);
            	}
            	
            	pointTable.add(pointRow);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("point table : "+pointTable);
        return pointTable;
    }

}
