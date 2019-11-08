import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";

@Component({
  selector: "app-relationship-profile",
  templateUrl: "./relationship-profile.component.html",
  styleUrls: ["./relationship-profile.component.scss"],
  animations: [routerTransition()]
})
export class RelationshipProfileComponent {
  // bar chart

  constructor() {}
}
