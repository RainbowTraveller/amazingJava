package com.miraie.springboot.restwebapp.survey;

import com.miraie.springboot.restwebapp.survey.data.Question;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Annotation creates an Integration Test
// This enables launching entire Spring Application
// Enabling to run the application on Random port instead of fixed on for avoiding clash.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyResourceIntegrationTest {
  // Testing URL : http://localhost:RANDOM_PORT/surveys/Survey1/Questions/Question1
  @Autowired // makes template aware of RANDOM_PORT
  private TestRestTemplate template;
  private static final String SPECIFIC_QUESTION_URL = "/surveys/Survey1/Questions/Question1";
  private static final String GENERIC_ALL_QUESTIONS_URL = "/surveys/Survey1/Questions";
  private static final String SPECIFIC_SURVEY_URL = "/surveys/Survey1";
  private static final String GENERIC_ALL_SURVEYS_URL = "/surveys";

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
   * Gives specific message when test fails : This is failed on purpose to test this functionality
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

  @Test
  public void TestGetQuestionFromSurvey_basic_refined() throws JSONException {
    ResponseEntity<String> responseEntity =
        template.getForEntity(SPECIFIC_QUESTION_URL, String.class);
    String expectedResponse =
        """
        {"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
        """;

    // Check the status code first
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    // Check the content type
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    // Check body content
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  @Test
  public void TestGetAllQuestionsForSurveyBasic() throws JSONException {
    ResponseEntity<String> responseEntity =
        template.getForEntity(GENERIC_ALL_QUESTIONS_URL, String.class);
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

    // Check the status code first
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    // Check the content type
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    // Check body content
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  @Test
  public void TestGetSurvey() throws JSONException {
    ResponseEntity<String> responseEntity =
        template.getForEntity(SPECIFIC_SURVEY_URL, String.class);
    String expectedResponse =
        """
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
        """;

    // Check the status code first
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    // Check the content type
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    // Check body content
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  @Test
  public void TestGetAllSurveys() throws JSONException {
    ResponseEntity<String> responseEntity =
        template.getForEntity(GENERIC_ALL_SURVEYS_URL, String.class);
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

    // Check the status code first
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    // Check the content type
    assertEquals("application/json", responseEntity.getHeaders().get("Content-Type").get(0));
    // Check body content
    JSONAssert.assertEquals(expectedResponse, responseEntity.getBody(), false);
  }

  /**
   * Testing the POST method by adding a new question
   */
  @Test
  public void addNewSurveyQuestion_BasicScenario() {
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
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
    ResponseEntity<String> responseEntity =
        template.exchange(GENERIC_ALL_QUESTIONS_URL, HttpMethod.POST, httpEntity, String.class);
    assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    String locationHeader = responseEntity.getHeaders().get("Location").get(0);
    assertTrue(
        locationHeader.contains("surveys/Survey1/Questions/"));

    // To remove any side effects after adding the new event, we need to delete this event
    // Some test cases which check the no. of events may fail if this test case
    // is executed before the other one. Now that was written earlier, it should not fail

    template.delete(locationHeader);
  }
}
