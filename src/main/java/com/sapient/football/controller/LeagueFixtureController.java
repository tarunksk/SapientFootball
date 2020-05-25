package com.sapient.football.controller;

import com.sapient.football.businessObjects.StandingsBO;
import com.sapient.football.service.LeagueFixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/league")
public class LeagueFixtureController {

    @Autowired
    private LeagueFixtureService leagueFixtureService;

    @GetMapping("/standings")
    public void standing(@RequestParam(name = "country_name", required = false) String countryName,
                         @RequestParam(name = "leagure_name", required = false) String leagueName,
                         @RequestParam(name = "team_name", required = false) String teamName){

        try{
            System.out.println(countryName);
            leagueFixtureService.getStandings(new StandingsBO(countryName, leagueName, teamName));
        }catch(Exception e){
        }
    }

}
