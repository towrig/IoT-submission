package org.superweatherman.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "forecast")
@AllArgsConstructor
@NoArgsConstructor
public class Forecast implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;

    private String locationName;

    private String nightWeather;

    private String dayWeather;

    private Long tempMin;

    private Long tempMax;

}
