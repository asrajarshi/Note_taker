package com.asrajarshi.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asrajarshi on 9/27/2015.
 */
public class GetJson {
    String json;
    int count;
    ArrayList<String> key;

    GetJson(String json) {
        this.json = json;
    }

    public List<ArrayList<String>> getIt() throws JSONException {
        JSONObject reader = new JSONObject(json);
        //Get the instance of JSONArray that contains JSONObjects
        JSONArray jsonArray = reader.optJSONArray("formules");
        //Iterate the jsonArray and print the info of JSONObjects
        key = gotIt(jsonArray);
        int cn = getCount();
        List<ArrayList<String>> ab11 = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ArrayList<String> ab1 = new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String[] value = new String[cn];
            for(int j =0;j<cn;j++){
                value[j] = jsonObject.optString(key.get(j)).toString(); // to retrive elemtns in one object, if used hashmap I could have put map.getValue
                ab1.add((value[j])); //add value to 1st column//
            }
            ab11.add(ab1);
        }
        return ab11;
    }
    // This method is to iterate over keys in one json object and to return those keys
    public ArrayList<String > gotIt(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        Iterator iterator = jsonObject.keys();
        ArrayList<String> key = new ArrayList<>();
        while (iterator.hasNext()) {
            key.add((String) iterator.next());
            count++;
        }
        return key;
    }
    public int getCount(){
        return count;
    }
}

