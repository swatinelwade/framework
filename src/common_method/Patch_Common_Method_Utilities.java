package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Patch_Common_Method_Utilities {
	
	public static void EvidenceFileCreator(String filename, String request, String response) throws IOException
	{
		File newtxtfile=new File("C:\\restAssuredEvidence\\" +filename+ ".txt");
		
		if(newtxtfile.createNewFile())
		{
			FileWriter datawriter=new FileWriter(newtxtfile);
			datawriter.write("Request Body is: " +request+ "\n\n");
			datawriter.write("Response Body is: " +response);
			datawriter.close();
			System.out.println("Request and Response body file is created. " +newtxtfile.getName());
		}
		else
		{
			System.out.println(newtxtfile.getName()+ "File already exists, take a backup of it");
		}
	}
}