package common_method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Put_Common_Method_Utilities {
	
	public static void EvidenceFileCreator(String filename, String Request, String Response) throws IOException
	{
		File newtxtfile= new File("C:\\restAssuredEvidence\\" +filename+ ".txt");
		
		if(newtxtfile.createNewFile())
		{
			FileWriter datawriter= new FileWriter(newtxtfile);
			datawriter.write("Request Body is: " +Request+ "\n\n");
			datawriter.write("Response Body is: " +Response);
			datawriter.close();
			System.out.println("Request and Response body saved in: " +newtxtfile.getName());
		}
		else
		{
			System.out.println(newtxtfile.getName()+ "File already exist, take backup of it.");
		}
	}
}
