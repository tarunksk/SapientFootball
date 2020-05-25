package com.sapient.football.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sapient.football.businessObjects.StandingsBO;

public interface LeagueFixtureService {
    void getStandings(StandingsBO standingsBO) throws JsonProcessingException;
}
