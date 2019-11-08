import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";

@Component({
  selector: "app-psych-tools",
  templateUrl: "./psych-tools.component.html",
  styleUrls: ["./psych-tools.component.scss"],
  animations: [routerTransition()]
})
export class PsychToolsComponent {
  // bar chart

  constructor() {}
}
