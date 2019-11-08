import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { routerTransition } from "../router.animations";
import { AuthService } from "../shared/services/auth.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  message: string;
  constructor(public router: Router, private authService: AuthService) {}

  ngOnInit() {}

  onLoggedin() {
    this.message = "";
    if (this.authService.login(this.username, this.password)) {
      this.password = "";
      this.router.navigate(["/home"]);
    } else {
      this.message = "Unable to login";
      this.router.navigate(["/login"]);
    }
  }
}
