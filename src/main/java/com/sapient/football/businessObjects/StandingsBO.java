package com.sapient.football.businessObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandingsBO {
    private String countryName;
    private String leagueName;
    private String teamName;
}
