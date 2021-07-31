package com.stepien.aedes.service.impl;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.stepien.aedes.dtos.HourlyWeatherDTO;
import com.stepien.aedes.dtos.WeatherAPIReturnDTO;
import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.Weather;
import com.stepien.aedes.repository.WeatherRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherInformationConsumerImpl implements WeatherInformationConsumer{

    Logger logger = LoggerFactory.getLogger(WeatherInformationConsumerImpl.class);
    
    @Autowired
    WeatherRepository weatherRepository;

    public static final String API_URL = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=LATITUDE&lon=LONGITUDE&dt=DAY_TIMESTAMP&units=metric&appid=API_ID";
    private String apiId;

    private final RestTemplate restTemplate;   

    public WeatherInformationConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;        
        this.apiId = "87e6f1bb128465961633de26a8d30e25";
        
    }

    /**
     * Gets the list of DTOs that come from a HTTP request from the weather API
     * @return
     */
    public WeatherAPIReturnDTO getCurrentWeatherInformation(Chunks chunk, Date date) {
        
        Double lat = chunk.getCentroid().getLat();
        Double lng = chunk.getCentroid().getLng();

        ResponseEntity<WeatherAPIReturnDTO> responseEntity = null;
        WeatherAPIReturnDTO weatherAPIReturnDTO = null;
        try {
            responseEntity = restTemplate.getForEntity(getPreparedUrl(lat.toString(), lng.toString(), date), WeatherAPIReturnDTO.class);
        } catch (RestClientException e){
            logger.error("Error while getting weather information", e);
        }

        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            weatherAPIReturnDTO = (WeatherAPIReturnDTO) responseEntity.getBody();
            saveLocationDayParameters(weatherAPIReturnDTO, chunk);
        } else {
            logger.error("We did not receive any information for chunk: " + chunk.getId());
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


    /**
     * Saves all the date information from the chunk. Since we are receiving the weather information for the whole day,
     * we process it before saving
     *
     * @param weatherAPIReturnDTO
     * @param chunk
     */
    private void saveLocationDayParameters(WeatherAPIReturnDTO weatherAPIReturnDTO, Chunks chunk) {


        Weather weather = new Weather();
        LocalDateTime today = LocalDateTime.now();   
        ZonedDateTime zdt = ZonedDateTime.ofInstant(today.minusDays(1).atZone(ZoneId.of("America/Sao_Paulo")).toInstant(), ZoneId.systemDefault());
        Calendar cal = GregorianCalendar.from(zdt);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);

        Date yesterday = cal.getTime();

        Double temp = 0.0;
        Double feels_like = 0.0;
        Double pressure = 0.0;
        Double humidity = 0.0;
        Double dew_point = 0.0;
        Double clouds = 0.0;
        Double visibility = 0.0;
        Double wind_speed = 0.0;
        Double wind_deg = 0.0;

        Double maxTemperature = -99999.0;
        Double minTemperature = 99999.0;
        Double maxHumidity = -99999.0;
        Double minHumidity = 99999.0;
        Double maxPressure = -99999.0;
        Double minPressure = 99999.0;

        for (HourlyWeatherDTO hourInfo :  weatherAPIReturnDTO.getHourly()) {
            temp += hourInfo.getTemp();
            feels_like += hourInfo.getFeels_like();
            pressure += hourInfo.getPressure();
            humidity += hourInfo.getHumidity();
            dew_point += hourInfo.getDew_point();
            clouds += hourInfo.getClouds();
            visibility += hourInfo.getVisibility();
            wind_speed += hourInfo.getWind_speed();
            wind_deg += hourInfo.getWind_deg();

            maxTemperature = Math.max(maxTemperature, hourInfo.getTemp());
            minTemperature = Math.min(minTemperature, hourInfo.getTemp());
            maxHumidity = Math.max(maxHumidity, hourInfo.getHumidity());
            minHumidity = Math.min(minHumidity, hourInfo.getHumidity());
            maxPressure = Math.max(maxPressure, hourInfo.getPressure());
            minPressure = Math.min(minPressure, hourInfo.getPressure());
        }

        int numberOfWeatherObjects = weatherAPIReturnDTO.getHourly().size();

        temp = temp/numberOfWeatherObjects;
        feels_like = feels_like/numberOfWeatherObjects;
        pressure = pressure/numberOfWeatherObjects;
        humidity = humidity/numberOfWeatherObjects;
        dew_point = dew_point/numberOfWeatherObjects;
        clouds = clouds/numberOfWeatherObjects;
        visibility = visibility/numberOfWeatherObjects;
        wind_speed = wind_speed/numberOfWeatherObjects;
        wind_deg = wind_deg/numberOfWeatherObjects;


        weather.setDate(yesterday);
        weather.setChunk(chunk);

        weather.setMaxTemperature(maxTemperature);
        weather.setMinTemperature(minTemperature);
        weather.setAvergeTemperature(temp);

        weather.setMaxHumidity(maxHumidity);
        weather.setMinHumidity(minHumidity);
        weather.setAvergeHumidity(humidity);

        weather.setMaxPressure(maxPressure);
        weather.setMinPressure(minPressure);
        weather.setAvergePressure(pressure);

        weatherRepository.save(weather);
    }

}
