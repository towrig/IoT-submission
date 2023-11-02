package org.superweatherman.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "forecast")
@AllArgsConstructor
@NoArgsConstructor
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String locationName;

    private String nightWeather;

    private String dayWeather;

    private Long tempMin;

    private Long tempMax;

}
