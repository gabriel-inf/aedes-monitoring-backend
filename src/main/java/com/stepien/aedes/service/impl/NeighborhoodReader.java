package com.stepien.aedes.service.impl;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.json.*;
import org.json.simple.parser.JSONParser;


public class NeighborhoodReader {

    public static final String jsonPath = "src/main/resources/geojson-bairros-poa.json";
    JSONParser parser = new JSONParser();

    public void getJson() throws FileNotFoundException {

        JSONObject jsonObject = new JSONObject(new FileReader(jsonPath));
        JSONArray jsonArray = new JSONArray(jsonObject);
        
        
    }

    
  



    

}
