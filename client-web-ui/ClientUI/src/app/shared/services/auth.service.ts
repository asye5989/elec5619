import { Injectable } from "@angular/core";
import { User } from "../domain/user";
import { Observable, BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AuthService {
  constructor() {}
  currentUser: User;
  public login(username: string, pass: string): boolean {
    let user: User = new User();
    user.username = username;
    user.fullname = "test user";
    user.token = "fake-token";
    user.id = 1;
    localStorage.setItem("user", JSON.stringify(user));

    return true;
  }

  public logout() {
    localStorage.removeItem("user");
  }

  public isLoginValid(): boolean {
    if (this.currentUser) return true;

    this.refreshCurrentUser();
    if (this.currentUser == null) return false;
  }

  private refreshCurrentUser(): User {
    if (this.currentUser) return this.currentUser;

    let jsonObj = localStorage.getItem("user");
    if (jsonObj) {
      let currentUserSubject = new BehaviorSubject<User>(JSON.parse(jsonObj));
      this.currentUser = currentUserSubject.value;
      return this.currentUser;
    } else {
      console.log("no user logged in");
      return null;
    }
  }

  public getJwtToken(): string {
    let token = null;
    if (this.currentUser == null) this.refreshCurrentUser();
    if (this.currentUser == null) return null;
    // at this point current user can not bebnull
    //
    return this.currentUser.token;
  }
}
