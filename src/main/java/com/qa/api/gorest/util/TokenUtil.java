package com.qa.api.gorest.util;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import io.restassured.path.json.JsonPath;

public class TokenUtil
{
    public static Map<Object, Object> appTokenMap;
    public static Map<String, String> tokenMap = new HashMap<String, String>();
    public static String clientID = "58758d3783e21df";

    public static Map<Object, Object> getAccessToken()
    {
        Map<String, String> formParams = new HashMap<String, String>();
        formParams.put("refresh_token", "39a17fdfc1fd914d2a9651aa74c8a4013044ab01");
        formParams.put("client_id", "66f4918ab1b4d24");
        formParams.put("client_secret", "3d45304d1fba70f9f67bc28993ed6e6c36f2f32f");
        formParams.put("grant_type", "refresh_token");
        
        JsonPath tokenJson = given().formParams(formParams).when().post("https://api.imgur.com/oauth2/token").then()
                .extract().jsonPath();
        
        System.out.println(tokenJson.getMap(""));
        appTokenMap = tokenJson.getMap("");
        return appTokenMap;
    }
    
    public static Map<String, String> getAuthToken()
    {
        // String authToken = appTokenMap.get("access_token").toString();
        // System.out.println("Auth Token ====> " +
        // "_2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");
        tokenMap.put("Authorization", "Bearer " + "_2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d");
        return tokenMap;
        
    }
    
    public static Map<String, String> getClientID()
    {

        tokenMap.put("Authorization", "Client-ID " + clientID);
        return tokenMap;
        
    }
}
