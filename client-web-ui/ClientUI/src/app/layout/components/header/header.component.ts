import { Component, OnInit } from "@angular/core";
import { Router, NavigationEnd } from "@angular/router";
import { TranslateService } from "@ngx-translate/core";
import { User } from "src/app/shared/domain/user";
import { UserService } from "src/app/shared";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ["./header.component.scss"]
})
export class HeaderComponent implements OnInit {
  public pushRightClass: string;
  private user: User;

  constructor(
    private translate: TranslateService,
    public router: Router,
    private userService: UserService
  ) {
    this.router.events.subscribe(val => {
      if (
        val instanceof NavigationEnd &&
        window.innerWidth <= 992 &&
        this.isToggled()
      ) {
        this.toggleSidebar();
      }
    });
  }

  ngOnInit() {
    this.pushRightClass = "push-right";
    // we should not have access to this point if not logge in
    this.user = this.userService.getUser();
  }

  isToggled(): boolean {
    const dom: Element = document.querySelector("body");
    return dom.classList.contains(this.pushRightClass);
  }

  toggleSidebar() {
    const dom: any = document.querySelector("body");
    dom.classList.toggle(this.pushRightClass);
  }

  rltAndLtr() {
    const dom: any = document.querySelector("body");
    dom.classList.toggle("rtl");
  }

  onLoggedout() {
    localStorage.removeItem("user");
  }

  changeLang(language: string) {
    this.translate.use(language);
  }
}
