import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";
import { MbtiQuestion } from "src/app/shared/domain/mbti-question";
import { NgForm } from "@angular/forms";
import { DatePipe } from "@angular/common";
import { PsychService } from "src/app/shared/services/psych.service";
import { QuestionResponse } from "src/app/shared/domain/question-response";
declare var $: any;

@Component({
  selector: "app-personality-type-test",
  templateUrl: "./personality-type-test.component.html",
  styleUrls: ["./personality-type-test.component.scss"],
  animations: [routerTransition()],
  providers: [DatePipe]
})
export class PersonalityTypeTestComponent implements OnInit {
  public questions: MbtiQuestion[];
  public resp: QuestionResponse[];
  date: string;
  testAllowed: boolean = true;
  testUnavailableMessage: string = null;

  constructor(private datePipe: DatePipe, private psychService: PsychService) {
    this.date = this.datePipe.transform(new Date(), "dd MMM, yyyy");
  }
  ngOnInit() {
    let x: [string, MbtiQuestion[]] = this.psychService.getMbtiQuestions();
    if (x[0] != null) {
      // error message
      this.testAllowed = false;
      this.formValid = false;
      this.testUnavailableMessage = x[0];
    } else {
      this.questions = x[1];
      this.resp = new Array<QuestionResponse>(this.questions.length);
      for (let i: number = 0; i < this.resp.length; i++) {
        this.resp[i] = new QuestionResponse(i);
      }
    }
  }

  private validateForm(): void {
    this.formValid = true;
    for (var i: number = 0; i < this.resp.length; i++) {
      if (this.resp[i].data == null) {
        this.formValid = false;
        $("#resp" + i).removeClass("d-none");
        $("#resp" + i).addClass("d-block");
      } else {
        $("#resp" + i).removeClass("d-block");
        $("#resp" + i).addClass("d-none");
      }
    }
  }

  private selectA() {
    for (var i: number = 0; i < this.questions.length; i++) {
      let id: string = "#resp" + i + "A";
      console.log("selecting option with id:" + id);
      $(id).trigger("click");
    }
    return false;
  }

  private formValid: boolean = false;

  submitForm(form: NgForm) {
    this.validateForm();
    if (this.formValid) {
      this.psychService.transmitResults(this.resp);
      this.testAllowed = false;
      this.testUnavailableMessage =
        "Response was successfully submitted. Please allow upto few minutes time to see updates.  You can retake the personality test after one month. For more information please refer to Admin. ";
    } else {
      console.log("Form validation false -- no transmittion of data ");
    }
  }

  resetForm(form: NgForm) {
    form.resetForm();
  }
}
