package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * This class is having all HTTP methods which will call the API's and having
 * generic methods for getting the response and fetch the values fom the
 * response.
 * @Author : Rajeevkumar
 */
public class RestClient
{
    
    // HTTP Methods :GET POST PUT DELETE
    /**
     * This method is used to call GET API
     *
     * @param contentType
     * @param baseURI
     * @param basePath
     * @param token
     * @param paramsMap
     * @param log
     * @return This method returns Response from the GET call
     */
    public static Response doGet(String contentType, String baseURI, String basePath, Map<String, String> token,
            Map<String, String> paramsMap, boolean log)
    {
        if (setBaseURI(baseURI))
        {
            RequestSpecification request = createRequest(contentType, token, paramsMap, log);
            Response response = getResponse("GET", request, basePath);
            return response;
        }
        return null;
    }
    
    /**
     * This method is used to call POST API
     *
     * @param contentType
     * @param baseURI
     * @param basePath
     * @param token
     * @param paramsMap
     * @param log
     * @param obj
     * @return This method returns Response from the POST call
     */
    public static Response doPost(String contentType, String baseURI, String basePath, Map<String, String> token,
            Map<String, String> paramsMap, boolean log, Object obj)
    {
        if (setBaseURI(baseURI))
        {
            RequestSpecification request = createRequest(contentType, token, paramsMap, log);
            addRequestPayLoad(request, obj);
            Response response = getResponse("POST", request, basePath);
            return response;
        }
        return null;
    }
    
    private static void addRequestPayLoad(RequestSpecification request, Object obj)
    {
        if (obj instanceof Map)
        {
            request.formParams((Map<String, String>) obj);
        } else
        {
            String jsonPayLoad = TestUtil.getSerializedJSON(obj);
            request.body(jsonPayLoad);
        }
    }
    
    private static boolean setBaseURI(String baseURI)
    {
        if (baseURI == null || baseURI.isEmpty())
        {
            System.out.println("Please pass a valid base URI  either it is blank/empty...");
            return false;
        } else
        {
            try
            {
                RestAssured.baseURI = baseURI;
                return true;
            } catch (Exception e)
            {
                System.out.println("Some exception occured while executing baseURI with RestAssured ");
                return false;
            }
        }
        
    }
    
    private static RequestSpecification createRequest(String contentType, Map<String, String> token,
            Map<String, String> paramsMap, boolean log)
    {
        RequestSpecification request;
        if (log)
        {
            request = RestAssured.given().log().all();
            
        } else
        {
            request = RestAssured.given();
        }
        if (token.size() > 0)
        {
            // request.header("Authorization", "Bearer " + token);
            request.headers(token);
        }
        if (!(paramsMap == null))
        {
            request.queryParams(paramsMap);
        }
        if (contentType != null)
        {
            if (contentType.equalsIgnoreCase("JSON"))
            {
                request.contentType(ContentType.JSON);
            } else if (contentType.equalsIgnoreCase("XML"))
            {
                request.contentType(ContentType.XML);
            } else if (contentType.equalsIgnoreCase("TEXT"))
            {
                request.contentType(ContentType.TEXT);
            } else if (contentType.equalsIgnoreCase("multipart"))
            {
                request.multiPart("image", new File("C:\\Users\\rajee\\OneDrive\\Desktop\\LowMercuryFish.png"));
            }
        }
        return request;
    }
    
    private static Response getResponse(String httpMethod, RequestSpecification request, String basePath)
    {
        return executeAPI(httpMethod, request, basePath);
    }
    
    private static Response executeAPI(String httpMethod, RequestSpecification request, String basePath)
    {
        Response response = null;
        
        switch (httpMethod)
        {
            case "GET":
                response = request.get(basePath);
                break;
            case "POST":
                response = request.post(basePath);
                break;
            case "PUT":
                response = request.put(basePath);
                break;
            case "DELETE":
                response = request.delete(basePath);
            default:
                System.out.println("Please pass a valid http method:");
                break;
        }
        return response;
    }
}
