import { Component, OnInit } from "@angular/core";
import { routerTransition } from "../../router.animations";
import { UserService } from "src/app/shared";

@Component({
  selector: "app-my-profile",
  templateUrl: "./my-profile.component.html",
  styleUrls: ["./my-profile.component.scss"],
  animations: [routerTransition()]
})
export class MyProfileComponent implements OnInit {
  name: string;
  personalityType: string;
  personalityTypeDetailInnerHTML: string;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.name = this.userService.getUser().fullname;

    let x = this.userService.getPersonalityDetails();
    if (x[0]) {
      this.personalityType = x[0];
      this.personalityTypeDetailInnerHTML = x[1];
    } else {
      this.personalityType = "Not Available";
      this.personalityTypeDetailInnerHTML =
        "<p>Please take the personality test. If you have already taken then wait few minutes and try again</p>";
    }
  }
}
