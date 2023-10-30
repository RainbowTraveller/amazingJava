package com.miraie.springboot.restwebapp.survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Annotation creates an Integration Test
// This enables launching entire Spring Application
// Enabling to run the application on Random port instead of fixed on for avoiding clash.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyResourceIntegrationTest {
  // Testing URL : http://localhost:RANDOM_PORT/surveys/Survey1/Questions/Question1
  String expectedReponse =
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
    }
    """;
  @Autowired // makes template aware of RANDOM_PORT
  private TestRestTemplate template;
  private static final String SPECIFIC_QUESTION_URL = "/surveys/Survey1/Questions/Question1";

  @Test
  public void TestGetQuestionFromSurvey_basic() {
    ResponseEntity<String> responseEntity =
        template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
    String expectedResponse =
        """
    {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
    """;
    assertEquals(expectedResponse.trim(), responseEntity.getBody());
    System.out.println(responseEntity.getBody());
    System.out.println(responseEntity.getHeaders());
  }
}
