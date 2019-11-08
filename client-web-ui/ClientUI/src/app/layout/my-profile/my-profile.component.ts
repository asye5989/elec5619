import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";

@Component({
  selector: "app-my-profile",
  templateUrl: "./my-profile.component.html",
  styleUrls: ["./my-profile.component.scss"],
  animations: [routerTransition()]
})
export class MyProfileComponent {
  // bar chart

  constructor() {}
}
