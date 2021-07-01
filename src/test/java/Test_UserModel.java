import API_Module.RequestModule.UserModelRequest;
import API_Module.ResponseModule.Content;
import API_Module.ResponseModule.UserModel;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class Test_UserModel extends  BaseTest{

    int user_id = 0;
    UserModel userModel;
    Content content;

// verification for valid user
    @Test(description = "verify that get user api response with valid userid")
    public void verifyGet_User_API(){
        String user_id ="16";
        Response getUserResponse = testSession_initiator.userModel_apiActions.requestUserModel_getUser_API(baseUrl+apiEndPoints.getValueFromDefaultPropertyFile("Get_Users"),user_id); // reading the end point from file and appending it to baseurl
        testSession_initiator.userModel_apiActions.printResponse(getUserResponse.asString()); //printing response on console
        testSession_initiator.userModel_apiActions.assertStatusCode(getUserResponse.statusCode(),200); // assertion for status code
        userModel = testSession_initiator.userModel_apiActions.getUserModelObject(getUserResponse); // implicit assertion validation schema by creating objects
        testSession_initiator.userModel_apiActions.assertNodeValues(userModel.getContent().get(0).getId().toString(),user_id); // assertion for user id node
    }

// verification for invalid user
    @Test(description = "verify that get user api response with invalid userid")
    public void verifyGet_User_API_For_Invalid_Id(){
        String user_id ="100";
        Response getUserResponse = testSession_initiator.userModel_apiActions.requestUserModel_getUser_API(baseUrl+apiEndPoints.getValueFromDefaultPropertyFile("Get_Users"),user_id);// reading the end point from file and appending it to baseurl
        testSession_initiator.userModel_apiActions.printResponse(getUserResponse.asString());//printing response on console
        testSession_initiator.userModel_apiActions.assertStatusCode(getUserResponse.statusCode(),200);// assertion for status code
        userModel = testSession_initiator.userModel_apiActions.getUserModelObject(getUserResponse); // implicit assertion validation schema by creating objects
        testSession_initiator.userModel_apiActions.assertNodeValues(userModel.getPage().getTotalElements().toString(),"0");// assertion for page array total elements

    }

    // implicit mapping of users response with pojo object for schema validation
    @Test(description = "Verify that get users api status code")
    public void verifyGet_Users_API(){
        Response getUserResponse = testSession_initiator.userModel_apiActions.requestUserModel_getUsers_API(baseUrl+apiEndPoints.getValueFromDefaultPropertyFile("Get_Users"));// reading the end point from file and appending it to baseurl
        testSession_initiator.userModel_apiActions.printResponse(getUserResponse.asString()); //print resposne
        userModel = testSession_initiator.userModel_apiActions.getUserModelObject(getUserResponse); // implicit assertion validation schema by creating objects
        testSession_initiator.userModel_apiActions.assertStatusCode(getUserResponse.statusCode(),200);// assertion of status code
    }


    // implicit mapping of users response with pojo object for schema validation
    @Test(description = "verify get users api total users size")
    public void verifyGet_Users_API_Elements_Size(){
        String totalSize = "20";
        Response getUserResponse = testSession_initiator.userModel_apiActions.requestUserModel_getUsers_API(baseUrl+apiEndPoints.getValueFromDefaultPropertyFile("Get_Users"));// reading the end point from file and appending it to baseurl
        testSession_initiator.userModel_apiActions.printResponse(getUserResponse.asString()); // print response
        userModel = testSession_initiator.userModel_apiActions.getUserModelObject(getUserResponse); // implicit assertion validation schema by creating objects
        testSession_initiator.userModel_apiActions.assertStatusCode(getUserResponse.statusCode(),200); // assert status code
        testSession_initiator.userModel_apiActions.assertNodeValues(userModel.getPage().getTotalElements().toString(),totalSize);// assert total size of elements with expected size
    }

    // implicit mapping of users request with pojo object for schema validation
    @Test(description = "verify post users api ")
    public void verifyPost_Users_API(){
        UserModelRequest userModelRequest = new UserModelRequest();
        String key = "78";
        userModelRequest.setFirstName("Ayush");
        userModelRequest.setLastName("Gaur");
        userModelRequest.setEmail("workingemail-"+key+"@gmail.com");
        userModelRequest.setDayOfBirth("1956-07-01");
        Response getUserResponse = testSession_initiator.userModel_apiActions.requestUserModel_postUser_API(baseUrl+apiEndPoints.getValueFromDefaultPropertyFile("Get_Users"),userModelRequest);// reading the end point from file and appending it to baseurl
        testSession_initiator.userModel_apiActions.printResponse(getUserResponse.asString()); // print response
        content = testSession_initiator.userModel_apiActions.getUserContentObject(getUserResponse); // implicit assertion validation schema by creating objects
        testSession_initiator.userModel_apiActions.assertStatusCode(getUserResponse.statusCode(),201); // assert status code
        testSession_initiator.userModel_apiActions.assertNodeValues(content.getEmail(),userModelRequest.getEmail()); // assertion for user is created with same email id provided
    }
    // implicit mapping of users request with pojo object for schema validation
    @Test(description = "verify post users api primaryKey violation")
    public void verifyPost_Users_API_With_Duplicate_EmailId(){
        UserModelRequest userModelRequest = new UserModelRequest();
        String key = "10";
        userModelRequest.setFirstName("Ayush");
        userModelRequest.setLastName("Gaur");
        userModelRequest.setEmail("workingemail-"+key+"@gmail.com");
        userModelRequest.setDayOfBirth("1956-07-01");
        Response getUserResponse = testSession_initiator.userModel_apiActions.requestUserModel_postUser_API(baseUrl+apiEndPoints.getValueFromDefaultPropertyFile("Get_Users"),userModelRequest);// reading the end point from file and appending it to baseurl
        testSession_initiator.userModel_apiActions.printResponse(getUserResponse.asString()); // print response
        content = testSession_initiator.userModel_apiActions.getUserContentObject(getUserResponse); // implicit assertion validation schema by creating objects
        testSession_initiator.userModel_apiActions.assertStatusCode(getUserResponse.statusCode(),409); // assert status code for duplicate entry response
    }


}
