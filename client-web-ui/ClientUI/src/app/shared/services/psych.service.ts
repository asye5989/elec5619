import { Injectable } from "@angular/core";
import { MbtiQuestion } from "../domain/mbti-question";
import { QuestionResponse } from "../domain/question-response";

@Injectable({
  providedIn: "root"
})
export class PsychService {
  constructor() {}

  getMbtiQuestions(): [string, MbtiQuestion[]] {
    let q: MbtiQuestion[] = [
      new MbtiQuestion(0, "Question 1 text: ", "option A", "Q1 optionB"),
      new MbtiQuestion(1, "Question 2 text: ", "option A", "Q2 optionB"),
      new MbtiQuestion(2, "Question 3 text: ", "option A", "Q3 optionB"),
      new MbtiQuestion(3, "Question 4 text: ", "option A", "Q4 optionB"),
      new MbtiQuestion(4, "Question 5 text: ", "option A", "Q5 optionB"),
      new MbtiQuestion(5, "Question 6 text: ", "option A", "Q6 optionB"),
      new MbtiQuestion(6, "Question 7 text: ", "option A", "Q7 optionB"),
      new MbtiQuestion(7, "Question 8 text: ", "option A", "Q8 optionB")
    ];
    let x: [string, MbtiQuestion[]] = [null, q];
    return x; //TODO integrtion
  }

  transmitResults(response: QuestionResponse[]): void {
    console.log("Transmit Simulation: " + JSON.stringify(response));
  }
}
