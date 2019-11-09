import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";
import { MyProfileComponent } from "../my-profile/my-profile.component";
import { UserService } from "src/app/shared";

@Component({
  selector: "app-my-Partner",
  templateUrl: "./my-partner.component.html",
  styleUrls: ["./my-partner.component.scss"],
  animations: [routerTransition()]
})
export class MyPartnerComponent {
  name: string;
  personalityType: string;
  personalityTypeDetailInnerHTML: string;

  constructor(private userService: UserService) {}

  ngOnInit() {
    let x = this.userService.getPersonalityDetailsOfPartner();
    if (x[0]) {
      // check if user has paertrner
      this.name = x[0];
      // now check if they have tyaken test
      if (x[1]) {
        this.personalityType = x[1];
        this.personalityTypeDetailInnerHTML = x[2];
      } else {
        this.personalityType = "Not Available";
        this.personalityTypeDetailInnerHTML =
          "<p>Please request partner to  the personality test. If he/she have already taken then wait few minutes and try again</p>";
      }
    } else {
      this.personalityType = "Un-Available";
      this.personalityTypeDetailInnerHTML =
        "<p> please contact Admin to make match </p>";
    }
  }
}
