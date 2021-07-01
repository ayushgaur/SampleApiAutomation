import API_Module.RequestModule.UserModel_ApiActions;

public class TestSession_Initiator {



    UserModel_ApiActions userModel_apiActions;
    public TestSession_Initiator(){
        init_Objects();
    }


    public void init_Objects(){
        userModel_apiActions = new UserModel_ApiActions();
    }
}
