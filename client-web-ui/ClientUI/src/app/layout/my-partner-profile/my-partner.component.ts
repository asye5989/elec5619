import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";

@Component({
  selector: "app-my-Partner",
  templateUrl: "./my-partner.component.html",
  styleUrls: ["./my-partner.component.scss"],
  animations: [routerTransition()]
})
export class MyPartnerComponent {
  // bar chart

  constructor() {}
}
