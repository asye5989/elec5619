import { Injectable } from "@angular/core";
import { User } from "../domain/user";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class UserService {
  constructor(private authService: AuthService) {}

  getUser(): User {
    return this.authService.getAuthenticatedCurrentUser();
  }

  getRelationshipScore(): number {
    return 100;
  }
}
