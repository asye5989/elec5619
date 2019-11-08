import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";
import { MbtiQuestion } from "src/app/shared/domain/mbti-question";

@Component({
  selector: "app-personality-type-test",
  templateUrl: "./personality-type-test.component.html",
  styleUrls: ["./personality-type-test.component.scss"],
  animations: [routerTransition()]
})
export class PersonalityTypeTestComponent implements OnInit {
  public questions: MbtiQuestion[];

  constructor() {}
  ngOnInit() {
    this.questions = [
      new MbtiQuestion(1, "text", "opta", "optB"),
      new MbtiQuestion(2, "text", "opta", "optB")
    ];
  }
  templateForm(form): void {
    console.log("");
  }
}
