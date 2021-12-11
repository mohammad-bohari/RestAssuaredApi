package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployees {
	
	
	@Test(dataProvider="empdataprovider")
	void postNewEmployees(String efname,String elname,String eemail, String edes)
	{
		RestAssured.baseURI = "http://localhost:8080/student";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//Here we created data whihc we can send along with the post request
		JSONObject requestParams=new JSONObject();
		
		requestParams.put("firstName",efname);
		requestParams.put("lastName",elname);
		requestParams.put("email",eemail);
		requestParams.put("programme",edes);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type","application/json");
		
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		//POST REQUEST
		Response response=httpRequest.request(Method.POST,"");
		
		
		//capture response body to perform validations
		
		String responseBody=response.getBody().asString();
		
		System.out.println("Response body is:"+ responseBody);
		
		Assert.assertEquals(responseBody.contains(efname),true);
		Assert.assertEquals(responseBody.contains(elname),true);
		Assert.assertEquals(responseBody.contains(eemail),true);
		Assert.assertEquals(responseBody.contains(edes),true);
		
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		
	}

	@DataProvider(name="empdataprovider")
	String [][] getEmpData() throws IOException
	{
		//Read data from excel
		String path=System.getProperty("user.dir")+"/src/test/java/datadriventesting/empdata.xlsx";
		
		int rownum=XLUtils.getRowCount(path,"Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1",1);
		
		String empdata[][]=new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				empdata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}

		}
		
		//String empdata[][]={ {"abc123","30000","40"}, {"xyz123","40000","30"}, {"pqr123","80000","50"}};
		
		
		return(empdata);
	}
	
	
}
