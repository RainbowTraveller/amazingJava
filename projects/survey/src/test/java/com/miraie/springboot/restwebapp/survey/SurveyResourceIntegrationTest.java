package com.miraie.springboot.restwebapp.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
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

  /**
   * Here we are using JSONAssert module. This enables taking some liberty as far as formatting is
   * concerned
   *
   * @throws JSONException
   */
  @Test
  public void TestGetQuestionFromSurvey_basic_json() throws JSONException {

    ResponseEntity<String> responseEntity =
        template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
    String expectedResponse =
        """
                  {"id":"Question1",
                  "description":"Most Popular Cloud Platform Today",
                  "options":["AWS","Azure","Google Cloud","Oracle Cloud"],
                  "correctAnswer":"AWS"}
        """;

    JSONAssert.assertEquals(expectedResponse.trim(), responseEntity.getBody(), true);
  }

  /**
   * Adding extra spaces
   *
   * @throws JSONException
   */
  @Test
  public void TestGetQuestionFromSurvey_basic_json_spaces() throws JSONException {

    ResponseEntity<String> responseEntity =
        template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
    String expectedResponse =
        """
                  {"id":"Question1",

                  "description":"Most Popular Cloud Platform Today",
                  "options":["AWS","Azure","Google Cloud","Oracle Cloud"],
                  "correctAnswer":"AWS"}

        """;

    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), true);
  }

  /**
   * Gives specific message when test fails
   *
   * <p>java.lang.AssertionError: Expected: options but none found
   *
   * @throws JSONException
   */
  @Test
  public void TestGetQuestionFromSurvey_basic_json_missingField() throws JSONException {

    String actualResponse =
        """
                  {"id":"Question1",
                  "description":"Most Popular Cloud Platform Today",
                  "correctAnswer":"AWS"}
        """;
    String expectedResponse =
        """
                  {"id":"Question1",
                  "description":"Most Popular Cloud Platform Today",
                  "options":["AWS","Azure","Google Cloud","Oracle Cloud"],
                  "correctAnswer":"AWS"}
        """;

    JSONAssert.assertEquals(expectedResponse, actualResponse, true);
  }

  /**
   * Non-strict check is also allowed the expected can be subset of the actual response
   *
   * @throws JSONException
   */
  @Test
  public void TestGetQuestionFromSurvey_basic_json_missingField_non_strict() throws JSONException {

    String expectedResponse =
        """
                  {"id":"Question1",
                  "description":"Most Popular Cloud Platform Today",
                  "correctAnswer":"AWS"}
        """;
    String actualResponse =
        """
                  {"id":"Question1",
                  "description":"Most Popular Cloud Platform Today",
                  "options":["AWS","Azure","Google Cloud","Oracle Cloud"],
                  "correctAnswer":"AWS"}
        """;

    JSONAssert.assertEquals(expectedResponse, actualResponse, false);
  }
}
