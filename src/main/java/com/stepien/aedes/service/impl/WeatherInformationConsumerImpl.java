package com.stepien.aedes.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stepien.aedes.dtos.WeatherAPIReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherInformationConsumerImpl implements WeatherInformationConsumer{

    Logger logger = LoggerFactory.getLogger(WeatherInformationConsumerImpl.class);

    public static final String API_URL = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=LATITUDE&lon=LONGITUDE&dt=DAY_TIMESTAMP&units=metric&appid=API_ID";
    private String apiId;
    private Date date;


    @Autowired
    private Environment env;

    private final RestTemplate restTemplate;   

    public WeatherInformationConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;        
        this.apiId = "87e6f1bb128465961633de26a8d30e25";
        
    }

    /**
     * Gets the list of DTOs that come from a HTTP request from the weather API
     * @return
     */
    public WeatherAPIReturnDTO getCurrentWeatherInformation(Double lat, Double lng, Date date) {
        
        ResponseEntity<WeatherAPIReturnDTO> responseEntity = null;
        WeatherAPIReturnDTO weatherAPIReturnDTO = null;
        try {
            logger.info(getPreparedUrl(lat.toString(), lng.toString(), date));
            responseEntity = restTemplate.getForEntity(getPreparedUrl(lat.toString(), lng.toString(), date), WeatherAPIReturnDTO.class);
            logger.info("We got a response!");
            logger.info(responseEntity.getBody().toString());
        } catch (RestClientException e){
            logger.error("Error while getting weather information", e);
        }

        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            // listOfWeatherDTO.addAll(Arrays.asList(responseEntity.getBody()));
            weatherAPIReturnDTO = (WeatherAPIReturnDTO) responseEntity.getBody();
        }

        return weatherAPIReturnDTO;
    }

    private String getPreparedUrl(String lat, String lng, Date date) {
        String preparedUrl = API_URL;        
        String dateTimestamp = Long.toString(Math.round(date.getTime()/1000));

        preparedUrl = preparedUrl.replace("LATITUDE", lat);
        preparedUrl = preparedUrl.replace("LONGITUDE", lng);
        preparedUrl = preparedUrl.replace("API_ID", this.apiId);
        preparedUrl = preparedUrl.replace("DAY_TIMESTAMP", dateTimestamp);
        return preparedUrl;
    }
}
