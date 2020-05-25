package com.sapient.football.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.football.businessObjects.StandingsBO;
import com.sapient.football.businessObjects.dto.CountryDto;
import com.sapient.football.businessObjects.dto.LeagueDto;
import com.sapient.football.client.HttpRestClient;
import com.sapient.football.service.LeagueFixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeagueFixtureServiceImpl implements LeagueFixtureService {

    private HttpRestClient httpRestClient;
    private ObjectMapper objectMapper;

    @Autowired
    public LeagueFixtureServiceImpl(HttpRestClient httpRestClient){
        this.httpRestClient = httpRestClient;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void getStandings(StandingsBO standingsBO) throws JsonProcessingException {
        String countryId = null;
        if(!StringUtils.isEmpty(standingsBO.getCountryName())){
            Map<String, String> queryParams = Collections.singletonMap("action", "get_countries");
            String response = httpRestClient.call(queryParams);

            List<CountryDto> countryDtos = objectMapper.readValue(response, new TypeReference<List<CountryDto>>() {
            });

            countryId = countryDtos.stream().filter(c -> c.getCountryName().equals(standingsBO.getCountryName())).findAny().map(CountryDto::getCountryId).orElse(null);

            if(!StringUtils.isEmpty(countryId)){
                return ;
            }
        }

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("action", "get_leagues");

        if(!StringUtils.isEmpty(countryId)){
            queryParams.put("country_id", countryId);
        }

        String response = httpRestClient.call(queryParams);

        List<LeagueDto> leagueDtos = objectMapper.readValue(response, new TypeReference<List<LeagueDto>>() {
        });


        if(!StringUtils.isEmpty(standingsBO.getLeagueName())){
            LeagueDto leagueDto = leagueDtos.stream().filter(c -> c.getLeagueName().equals(standingsBO.getLeagueName())).findAny().orElse(null);

            if(leagueDto == null) {
                return;
            }
        }

    }
}
