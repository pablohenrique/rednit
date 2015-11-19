package com.rednit.app.Controller;

import com.rednit.app.Model.ResultPerson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablohenrique on 9/28/15.
 */
public class ResultPersonList {

    private ArrayList<ResultPerson> personArrayList = new ArrayList<ResultPerson>();

    public ResultPersonList() { }

    public void loadFromJSONArray(JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i++){
            add(new ResultPerson((JSONObject) jsonArray.get(i)));
        }
    }

    public void add(ResultPerson resultPerson){
        personArrayList.add(resultPerson);
    }

    public List<String> getNames(){
        List<String> resultList = new ArrayList<String>();
        if(!personArrayList.isEmpty()){
            for(ResultPerson person : personArrayList){
                resultList.add(person.getFullName());
            }
        }
        return resultList;
    }

    public List<String> getProfileUrls(){
        List<String> resultList = new ArrayList<String>();
        if(!personArrayList.isEmpty()){
            for(ResultPerson person : personArrayList){
                resultList.add(person.getPictureProfile());
            }
        }
        return resultList;
    }

    public void generateMockResults(){
        String[] names = {
                "John",
                "Doe",
                "Fram",
                "Crim"
        };
        String[] lastNames = {
                "Trip",
                "Hoe",
                "Fuck",
                "Dim"
        };
        String[] social = {
                "Facebook",
                "Facebook",
                "Twitter",
                "Facebook"
        };

        String[] identifiers = {
                "1234",
                "5678",
                "9101112",
                "13141516"
        };
        String[] urls = {
                "http://lorempixel.com/200/200/people/6/",
                "http://lorempixel.com/200/200/people/7/",
                "http://lorempixel.com/200/200/people/8/",
                "http://lorempixel.com/200/200/people/9/"
        };

        for(int i = 0; i < names.length; i++){
            personArrayList.add(new ResultPerson((long)i, identifiers[i], social[i], names[i], lastNames[i], urls[i]));
        }

    }

}
