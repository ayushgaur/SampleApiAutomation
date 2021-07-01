import Utility_Module.PropertyFile_Reader;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseTest {

    TestSession_Initiator testSession_initiator;
    PropertyFile_Reader defaultProperties;
    PropertyFile_Reader apiEndPoints;
    String baseUrl= new String();

    @BeforeTest
    public void beforeTest(){
        testSession_initiator = new TestSession_Initiator();
        defaultProperties = new PropertyFile_Reader("DefaultConfig.properties");
        apiEndPoints = new PropertyFile_Reader("apiEndpoints.properties");
        baseUrl = defaultProperties.getValueFromDefaultPropertyFile("BaseUrl");
    }
}
