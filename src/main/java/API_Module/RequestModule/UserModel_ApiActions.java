package API_Module.RequestModule;

import API_Module.ApiActionHelper;
import API_Module.ResponseModule.Content;
import API_Module.ResponseModule.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UserModel_ApiActions extends ApiActionHelper {

    UserModel userModel = new UserModel();

    public Response requestUserModel_getUser_API(String url,String id){
        System.out.println("url"+url);
        try {
            return RestAssured.given().param("id",id).when().get(new URI(url)).then().extract().response();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }return null;
    }

    public Response requestUserModel_getUsers_API(String url){
        try {
            return RestAssured.given().when().get(new URI(url)).then().extract().response();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }return null;
    }

    public Response requestUserModel_postUser_API(String url, UserModelRequest body){
        try {
            try {
                return RestAssured.given().contentType("application/json").body(objectMapper.writeValueAsString(body)).when().post(new URI(url)).then().extract().response();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }return null;
    }

    public UserModel getUserModelObject(Response userModel){
        try {
            return objectMapper.readValue(userModel.asString(),UserModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }

    public Content getUserContentObject(Response userContent){
        try {
            return objectMapper.readValue(userContent.asString(),Content.class);
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }
}
