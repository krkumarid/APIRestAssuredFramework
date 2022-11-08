package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TokenUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Epic -101: POST Images to Imgur API ")
@Feature("US-1009 :POST Image apti feature")
public class GetImgurAPITest
{
    Map<Object, Object> tokenMap;
    public static Map<String, String> authTokenMap = new HashMap<String, String>();
    String accessToken;
    String accountUserName;
    String refreshToken;

    String baseUri = "https://api.imgur.com";
    
    @BeforeMethod
    public void setUp()
    {
        tokenMap = TokenUtil.getAccessToken();
        accessToken = tokenMap.get("access_token").toString();
        accountUserName = tokenMap.get("account_username").toString();
        refreshToken = tokenMap.get("refresh_token").toString();
        
    }
    
    @Test
    public void getAccoutBlockStatus()
    {
        authTokenMap.put("Authorization", "Bearer " + accessToken);
        String basePath = "/account/v1/" + accountUserName + "/block";
        Response response = RestClient.doGet(null, baseUri, basePath, authTokenMap, null, true);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test
    public void getAccoutImages()
    {
        authTokenMap.put("Authorization", "Bearer " + accessToken);
        String basePath = "/3/account/me/images";
        Response response = RestClient.doGet(null, baseUri, basePath, authTokenMap, null, true);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Description("Upload image")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void uploadImagePostAPI_Test()
    {
        String basePath = "/3/upload";
        Map<String, String> clientIdMap = TokenUtil.getClientID();
        Map<String, String> formMap = new HashMap<String, String>();
        formMap.put("title", "test title API");
        formMap.put("description", "test description API");

        Response response = RestClient.doPost("multipart", baseUri, basePath, clientIdMap, null, true, formMap);
        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

}
