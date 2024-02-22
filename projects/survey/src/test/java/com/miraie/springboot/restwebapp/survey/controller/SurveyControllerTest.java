package com.miraie.springboot.restwebapp.survey.controller;

import com.miraie.springboot.restwebapp.survey.data.Question;
import com.miraie.springboot.restwebapp.survey.data.Survey;
import com.miraie.springboot.restwebapp.survey.service.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = SurveyController.class)
class SurveyControllerTest {

  @MockBean SurveyService mockSurveyService;

  @Autowired MockMvc mockMvc;
  private static final String SPECIFIC_QUESTION_URL =
      "http://localhost:8080/surveys/Survey1/Questions/Question1";
  private static final String SPECIFIC_SURVEY_URL = "http://localhost:8080/surveys/Survey1";
  private static final String GENERIC_QUESTION_URL =
      "http://localhost:8080/surveys/Survey1/Questions";
  private static final String GENERIC_SURVEY_URL = "http://localhost:8080/surveys";

  /*
   * Variables to be used throughout
   * */
  private List<Survey> surveys = new ArrayList<>();

  Survey survey;
  Question question1, question2, question3;
  List<Question> questions;

  @BeforeEach
  void prepareStateToTest() {

    question1 =
        new Question(
            "Question1",
            "Most Popular Cloud Platform Today",
            Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"),
            "AWS");
    question2 =
        new Question(
            "Question2",
            "Fastest Growing Cloud Platform",
            Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"),
            "Google Cloud");
    question3 =
        new Question(
            "Question3",
            "Most Popular DevOps Tool",
            Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"),
            "Kubernetes");

    questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

    survey = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

    surveys.add(survey);
  }

  @Test
  void getQuestionFromSurvey_404() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(404, mvcResult.getResponse().getStatus());
  }

  @Test
  void getQuestionFromSurvey_basicScenario() throws Exception {
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(SPECIFIC_QUESTION_URL).accept(MediaType.APPLICATION_JSON);
    when(mockSurveyService.getQuestionFromSurvey("Survey1", "Question1")).thenReturn(question1);
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(200, mvcResult.getResponse().getStatus());
    String expectedResponse =
        """
            {
                    "id": "Question1",
                    "description": "Most Popular Cloud Platform Today",
                    "options": [
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "Oracle Cloud"
                    ],
                    "correctAnswer": "AWS"
                  },
""";
    JSONAssert.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString(), false);
  }

  /** This is a test for post method which adds as new question to the survey */
  @Test
  void addNewQuestion() throws Exception {

    String requestBody =
        """
                      {
                        "description": "Adding a new Questions using integration tests",
                        "options": [
                          "Java",
                          "Golang",
                          "Python",
                          "Rust"
                        ],
                        "correctAnswer": "Jave"
                      }
    """;
    // Mock for returning some id when a new question is added
    when(mockSurveyService.addNewQuestion(anyString(), any())).thenReturn("New_Id");
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(GENERIC_QUESTION_URL)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON);
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(201, mvcResult.getResponse().getStatus());
    // Try using printing and copy the location url of the new resource
    // to add assert
    // System.out.println(mvcResult.getResponse().getHeader("Location"));
    String locationHeader = mvcResult.getResponse().getHeader("Location");
    assert locationHeader != null;
    assertTrue(locationHeader.contains("http://localhost:8080/surveys/Survey1/Questions/New_Id"));
  }

  @Test
  void getAllSurveys() throws Exception {
    String expectedResponse =
        """
            [
              {
                "id": "Survey1",
                "title": "My Favorite Survey",
                "description": "Description of the Survey",
                "questions": [
                  {
                    "id": "Question1",
                    "description": "Most Popular Cloud Platform Today",
                    "options": [
                    "AWS",
                    "Azure",
                    "Google Cloud",
                    "Oracle Cloud"
                    ],
                    "correctAnswer": "AWS"
                  },
                  {
                    "id": "Question2",
                    "description": "Fastest Growing Cloud Platform",
                    "options": [
                      "AWS",
                      "Azure",
                      "Google Cloud",
                      "Oracle Cloud"
                    ],
                    "correctAnswer": "Google Cloud"
                  },
                  {
                    "id": "Question3",
                    "description": "Most Popular DevOps Tool",
                    "options": [
                      "Kubernetes",
                      "Docker",
                      "Terraform",
                      "Azure DevOps"
                    ],
                    "correctAnswer": "Kubernetes"
                  }
                ]
              }
            ]
""";
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(GENERIC_SURVEY_URL).contentType(MediaType.APPLICATION_JSON);
    when(mockSurveyService.getAllSurveys()).thenReturn(surveys);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(200, result.getResponse().getStatus());
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  void getSurvey() throws Exception {

    String expectedResponse =
        """
                  {
                    "id": "Survey1",
                    "title": "My Favorite Survey",
                    "description": "Description of the Survey"
                  }
    """;
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(SPECIFIC_SURVEY_URL).contentType(MediaType.APPLICATION_JSON);
    when(mockSurveyService.getSurvey("Survey1")).thenReturn(survey);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(200, result.getResponse().getStatus());
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  void getAllQuestions() throws Exception {
    String expectedResponse =
        """
                [
                    {
                      "id": "Question1"
                    },
                    {
                      "id": "Question2"
                    },
                    {
                      "id": "Question3"
                    }
                ]
                """;
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(GENERIC_QUESTION_URL).contentType(MediaType.APPLICATION_JSON);
    when(mockSurveyService.getAllQuestionsForSurvey("Survey1")).thenReturn(questions);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    assertEquals(200, result.getResponse().getStatus());
    JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
  }

  @Test
  void deleteQuestionFromSurvey() {}

  @Test
  void updateQuestionFromSurvey() throws Exception {
    String requestBody =
        """
                          {
                            "description": "Adding a new Questions using integration tests",
                            "options": [
                              "Java",
                              "Golang",
                              "Golang",
                              "Rust",
                              "Python"
                              "C",
                              "C++"
                            ],
                            "correctAnswer": "Rust"
                          }
        """;

    Question question1 =
        new Question(
            "Question1",
            "Adding a new Questions using integration tests",
            Arrays.asList("Java", "Golang", "Golang", "Rust", "Python", "C", "C++"),
            "Java");
    // Mock for returning some id when a new question is added
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.put(GENERIC_QUESTION_URL)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody)
            .contentType(MediaType.APPLICATION_JSON);
    MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
    assertEquals(200, mvcResult.getResponse().getStatus());
    String locationHeader = mvcResult.getResponse().getHeader("Location");
//    assert locationHeader != null;
//    assertTrue(locationHeader.contains("http://localhost:8080/surveys/Survey1/Questions/Question1"));
  }
}
