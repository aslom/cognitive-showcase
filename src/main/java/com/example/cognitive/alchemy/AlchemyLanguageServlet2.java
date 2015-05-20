package com.example.cognitive.alchemy;

import com.alchemyapi.api.AlchemyAPI_Params;
import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alchemyapi.api.AlchemyAPI;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class AlchemyLanguageServlet2 extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Create an AlchemyAPI object.
        AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("9f55b116a2bc68aa9f4fbedb3f630f8f55f27f30");

        // Extract a ranked list of named entities for a web URL.
        Document doc;
		try {

	        // Extract a ranked list of named entities from a text string.
			String words2 = request.getParameter("words2");
	        doc = alchemyObj.TextGetRankedNamedEntities(words2);
	        PrintWriter out2 = response.getWriter();
	        out2.println (
	                  "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" +" +
	                      "http://www.w3.org/TR/html4/loose.dtd\">\n" +
	                  "<html> \n" +
	                    "<head> \n" +
	                      "<meta http-equiv=\"Content-Type\" content=\"text/html; " +
	                        "charset=ISO-8859-1\"> \n" +
	                      "<title> Crunchify.com JSP Servlet Example  </title> \n" +
	                    "</head> \n" +
	                    "<body> <div align='center'> \n" +
	                      "<style= \"font-size=\"12px\" color='black'\"" + "\">" +
	                      	getStringFromDocument(doc) + 
	                    "</font></body> \n" +
	                  "</html>" 
	                ); 
	        
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // utility function
    private static String getFileContents(String filename)
        throws IOException, FileNotFoundException
    {
        File file = new File(filename);
        StringBuilder contents = new StringBuilder();

        BufferedReader input = new BufferedReader(new FileReader(file));

        try {
            String line = null;

            while ((line = input.readLine()) != null) {
                contents.append(line);
                contents.append(System.getProperty("line.separator"));
            }
        } finally {
            input.close();
        }

        return contents.toString();
    }

    // utility method
    private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

