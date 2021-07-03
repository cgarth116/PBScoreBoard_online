package ru.irbish.pbscoreboard.additionalMod;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

public interface ExternalModules {

    default String            getUrlContent(String urlAdress){
        StringBuffer content = new StringBuffer();

        try{
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e){
            System.out.println("Город не найден");
        }
        return  content.toString();
    }    //for json load
    default ArrayList<String> getCityList(){
        ArrayList<String> cityArray = new ArrayList<>();
        String output = getUrlContent("https://api.hh.ru/areas");                     //берем json

        if(!output.isEmpty()){
            JSONArray values = new JSONArray(output);
            for (int cou = 0; cou < values.length(); cou++) {
                JSONArray areas = values.getJSONObject(cou).getJSONArray("areas");        //берем страну
                for (int reg = 0; reg < areas.length(); reg++) {
                    JSONArray city = areas.getJSONObject(reg).getJSONArray("areas");      //берем регион
                    for (int cit = 0; cit < city.length(); cit++) {
                        cityArray.add(city.getJSONObject(cit).getString("name"));         //берем город
                    }
                }
            }
        }
        //add manually city which not in list from json
        cityArray.add("Москва");
        cityArray.add("Санкт-Петербург");

        Collections.sort(cityArray);
        return cityArray;
    }                      //create city list for choose
}
