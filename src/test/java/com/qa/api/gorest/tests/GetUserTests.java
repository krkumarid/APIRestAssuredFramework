package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TokenUtil;

import io.restassured.response.Response;

public class GetUserTests
{
    String baseURI = "https://gorest.co.in";
    String basePath = "/public/v2/users/";
    String token = "_2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d";

    public static Map<String, String> authTokenMap = new HashMap<String, String>();

    @Test(priority = 1)
    public void getAllUserList_API_Test()
    {
        authTokenMap = TokenUtil.getAuthToken();
        Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, null, true);

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

    @Test(priority = 2)
    public void getUserListWith_QueryParam_API_Test()
    {
        authTokenMap = TokenUtil.getAuthToken();
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "3546");
        params.put("name", "Harinarayanan");
        params.put("gender", "male");
        Response response = RestClient.doGet("JSON", baseURI, basePath, authTokenMap, params, true);

        System.out.println(response.getStatusCode());
        System.out.println(response.prettyPrint());
    }
}
