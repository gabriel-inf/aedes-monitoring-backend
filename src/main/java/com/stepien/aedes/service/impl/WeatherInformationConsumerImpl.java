package com.stepien.aedes.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stepien.aedes.dtos.WeatherInformationDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Configurable
public class WeatherInformationConsumerImpl implements WeatherInformationConsumer{

    Logger logger = LoggerFactory.getLogger(WeatherInformationConsumerImpl.class);

    public static final String API_URL = "https://api.aerisapi.com/conditions/LATITUDE,LONGITUDE?client_id=CLIENT_ID&client_secret=CLIENT_SECRET";

    private String clientId;
    private String clientSecret;


    @Autowired
    private Environment env;

    private final RestTemplate restTemplate;   

    public WeatherInformationConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.clientId = "FOiL5ySaJqRuqDsBVQtwI"; //env.getProperty("weather.clientId"); 
        this.clientSecret = "rEJl8GhlddMD1ckkVhGHYlwL5bRGL2uUof3ulzz7";//env.getProperty("weather.clientSecret");
    }

    /**
     * Gets the list of DTOs that come from a HTTP request from the weather API
     * @return
     */
    public List<WeatherInformationDTO> getCurrentWeatherInformation(String lat, String lng) {
        
        ResponseEntity<Object> responseEntity = null;
        List<WeatherInformationDTO> listOfWeatherDTO = new ArrayList<>();
        
        try {
            logger.info(getPreparedUrl(lat, lng));
            responseEntity = restTemplate.getForEntity(getPreparedUrl(lat, lng), Object.class);
            logger.info("We got a response!");
            logger.info(responseEntity.getBody().toString());
        } catch (RestClientException e){
            logger.error("Error while getting weather information", e);
        }

        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            // listOfWeatherDTO.addAll(Arrays.asList(responseEntity.getBody()));
        }

        return listOfWeatherDTO;
    }

    private String getPreparedUrl(String lat, String lng) {
        String preparedUrl = API_URL;
        preparedUrl = preparedUrl.replace("LATITUDE", lat);
        preparedUrl = preparedUrl.replace("LONGITUDE", lng);
        preparedUrl = preparedUrl.replace("CLIENT_ID", this.clientId);
        preparedUrl = preparedUrl.replace("CLIENT_SECRET", this.clientSecret);

        return preparedUrl;
    }
}
