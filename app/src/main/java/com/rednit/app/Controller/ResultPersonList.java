package com.rednit.app.Controller;

import com.rednit.app.Model.ResultPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablohenrique on 9/28/15.
 */
public class ResultPersonList {

    private ArrayList<ResultPerson> personArrayList = new ArrayList<ResultPerson>();

    public ResultPersonList() { }

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
                "http://www.mouserunner.com/images/FOwebsite_SinglePreview.png",
                "http://www.mouserunner.com/images/FOwebsite_SinglePreview.png",
                "http://www.mouserunner.com/images/FOwebsite_SinglePreview.png",
                "http://www.mouserunner.com/images/FOwebsite_SinglePreview.png"
        };

        for(int i = 0; i < names.length; i++){
            personArrayList.add(new ResultPerson((long)i, identifiers[i], social[i], names[i], lastNames[i], urls[i]));
        }

    }

}
