package com.qa.api.gorest.tests;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.api.gorest.util.TokenUtil;

import io.restassured.response.Response;

/**
 * @author Rajeevkumar
 */
public class CreateUserTests
{
    String baseURI = "https://gorest.co.in";
    String basePath = "/public/v2/users";
    String token = "2103500286073c509d810e8ae8ee9e1b56f3171d8cfefd26680daa5059a0e27d";
    
    /**
     * @return
     */
    @DataProvider
    public Object[][] getUserData()
    {
        Object userData[][] = ExcelUtil.getTestData("userdata");
        return userData;
    }
    
    @Test(dataProvider = "getUserData")
    public void creatrUserAPI_POST_Test(String name, String email, String gender, String status)
    {
        // User user = new User("MuthuChechi", "muthuchechi123@gmail.com", "female",
        // "active");
        Map<String, String> authTokenMap = TokenUtil.getAuthToken();
        User user = new User(name, email, gender, status);
        Response response = RestClient.doPost("JSON", baseURI, basePath, authTokenMap, null, true, user);
        System.out.println(response.statusCode());
        System.out.println(response.prettyPrint());
        System.out.println("===========================================");
    }
}
