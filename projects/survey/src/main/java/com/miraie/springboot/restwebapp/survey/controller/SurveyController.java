package com.miraie.springboot.restwebapp.survey.controller;

import com.miraie.springboot.restwebapp.survey.data.Question;
import com.miraie.springboot.restwebapp.survey.data.Survey;
import com.miraie.springboot.restwebapp.survey.service.SurveyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
  public void addNewQuestion(@PathVariable String id, @RequestBody Question question) {
    service.addNewQuestion(id, question);
  }
}
