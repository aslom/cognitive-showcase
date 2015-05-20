package com.example.cognitive.qa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.question_and_answer.v1.QuestionAndAnswer;
import com.ibm.watson.developer_cloud.question_and_answer.v1.model.Answer;
import com.ibm.watson.developer_cloud.question_and_answer.v1.model.QuestionAndAnswerDataset;
import com.ibm.watson.developer_cloud.question_and_answer.v1.model.WatsonAnswer;

import java.io.PrintWriter;
 
/**
 * @author Crunchify.com
 */
 
public class QAServlet extends HttpServlet 
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // reading the user input
    	QuestionAndAnswer service = new QuestionAndAnswer();
		service.setUsernameAndPassword("a4646f38-b4ca-40ad-b68b-2cd161a477df", "fs9igNJrUcsY");
		service.setDataset(QuestionAndAnswerDataset.HEALTHCARE);
    	
    	String question = request.getParameter("question");
    	WatsonAnswer pipelines = service.ask(question);
		System.out.println(pipelines);
		String answers = "";
		for (int i = 0; i < pipelines.getAnswers().size(); i++)
		{
			Answer answer = pipelines.getAnswers().get(i);
			System.out.println(answer);
			answers = answers + answer.getText();
		}
        PrintWriter out = response.getWriter();
        out.println (
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
                      	answers + 
                    "</font></body> \n" +
                  "</html>" 
                ); 
    }
}
