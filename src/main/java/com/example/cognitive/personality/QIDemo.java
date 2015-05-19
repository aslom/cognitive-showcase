package com.example.cognitive.personality;

import java.util.List;

import com.ibm.watson.developer_cloud.question_and_answer.v1.QuestionAndAnswer;
import com.ibm.watson.developer_cloud.question_and_answer.v1.model.Pipeline;
import com.ibm.watson.developer_cloud.question_and_answer.v1.model.QuestionAndAnswerDataset;
import com.ibm.watson.developer_cloud.question_and_answer.v1.model.WatsonAnswer;

public class QIDemo 
{
	public static void main(String[] args)
	{
		
		QuestionAndAnswer service = new QuestionAndAnswer();
		service.setUsernameAndPassword("a4646f38-b4ca-40ad-b68b-2cd161a477df", "fs9igNJrUcsY");

		service.setDataset(QuestionAndAnswerDataset.HEALTHCARE);
		WatsonAnswer pipelines = service.ask("What is HIV?");
		System.out.println(pipelines);
	}
}
