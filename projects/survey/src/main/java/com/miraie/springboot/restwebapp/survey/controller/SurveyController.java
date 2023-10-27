package com.miraie.springboot.restwebapp.survey.controller;

import com.miraie.springboot.restwebapp.survey.data.Question;
import com.miraie.springboot.restwebapp.survey.data.Survey;
import com.miraie.springboot.restwebapp.survey.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SurveyController {

  public SurveyService service;

  public SurveyController(SurveyService service) {
    super();
    this.service = service;
  }

  @RequestMapping("/surveys")
  public List<Survey> getAllSurveys() {
    return service.getAllSurveys();
  }

  @RequestMapping("/surveys/{id}")
  public Survey getSurvey(@PathVariable String id) {
    Survey survey = service.getSurvey(id);
    if (survey == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return survey;
  }

  @RequestMapping("/surveys/{id}/Questions")
  public List<Question> getAllQuestions(@PathVariable String id) {
    List<Question> questionsForSurvey = service.getAllQuestionsForSurvey(id);
    if (questionsForSurvey == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return questionsForSurvey;
  }

  @RequestMapping("/surveys/{id}/Questions/{questionId}")
  public Question getQuestionFromSurvey(@PathVariable String id, @PathVariable String questionId) {
    Question questionFromSurvey = service.getQuestionFromSurvey(id, questionId);
    if (questionFromSurvey == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return questionFromSurvey;
  }

  @RequestMapping(value = "/surveys/{id}/Questions", method = RequestMethod.POST)
  public ResponseEntity<Object> addNewQuestion(
      @PathVariable String id, @RequestBody Question question) {
    String questionId = service.addNewQuestion(id, question);
    // This will work as well but as per REST standards when an entity is created then the response
    // to be returned is 201. We can add the location of the URI as well

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{questionId}")
            .buildAndExpand(questionId)
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @RequestMapping(value = "/surveys/{id}/Questions/{questionId}", method = RequestMethod.DELETE)
  public ResponseEntity<Object> deleteQuestionFromSurvey(
      @PathVariable String id, @PathVariable String questionId) {
    service.deleteQuestionFromSurvey(id, questionId);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping(value = "/surveys/{id}/Questions/{questionId}", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateQuestionFromSurvey(
      @PathVariable String id, @PathVariable String questionId, @RequestBody Question question) {
    service.updateQuestionFromSurvey(id, questionId, question);
    return ResponseEntity.noContent().build();
  }
}
