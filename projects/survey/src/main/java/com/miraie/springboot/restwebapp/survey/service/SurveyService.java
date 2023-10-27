package com.miraie.springboot.restwebapp.survey.service;

import com.miraie.springboot.restwebapp.survey.data.Question;
import com.miraie.springboot.restwebapp.survey.data.Survey;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Service
public class SurveyService {

  private static List<Survey> surveys = new ArrayList<>();

  static {
    Question question1 =
        new Question(
            "Question1",
            "Most Popular Cloud Platform Today",
            Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"),
            "AWS");
    Question question2 =
        new Question(
            "Question2",
            "Fastest Growing Cloud Platform",
            Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"),
            "Google Cloud");
    Question question3 =
        new Question(
            "Question3",
            "Most Popular DevOps Tool",
            Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"),
            "Kubernetes");

    List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

    Survey survey =
        new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

    surveys.add(survey);
  }

  public List<Survey> getAllSurveys() {
    return surveys;
  }

  public Survey getSurvey(String surveyId) {
    return surveys.stream()
        .filter(survey -> surveyId.equalsIgnoreCase(survey.getId()))
        .findAny()
        .orElse(null);
  }

  public Question getQuestionFromSurvey(String id, String questionId) {
    Survey currSurvey = getSurvey(id);
    return currSurvey.getQuestions().stream()
        .filter(question -> questionId.equalsIgnoreCase(question.getId()))
        .findFirst()
        .orElse(null);
  }

  public List<Question> getAllQuestionsForSurvey(String id) {
    Survey currSurvey = getSurvey(id);
    if (currSurvey != null) {
      return currSurvey.getQuestions();
    }
    return null;
  }

  public String addNewQuestion(String id, Question question) {
    List<Question> questionsForSurvey = getAllQuestionsForSurvey(id);
    question.setId(getRandomId());
    questionsForSurvey.add(question);
    return question.getId();
  }

  private static String getRandomId() {
    SecureRandom secureRandom = new SecureRandom();
    String randomId = new BigInteger(32, secureRandom).toString();
    return randomId;
  }
}
