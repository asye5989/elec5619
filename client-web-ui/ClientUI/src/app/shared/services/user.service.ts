import { Injectable } from "@angular/core";
import { User } from "../domain/user";
import { AuthService } from "./auth.service";

@Injectable({
  providedIn: "root"
})
export class UserService {
  constructor(private authService: AuthService) {}

  public getUser(): User {
    return this.authService.getAuthenticatedCurrentUser();
  }

  public getRelationshipStats(): [string[], number[]] {
    return [["01-10-2019", "01-11-2019"], [60, 80]];
  }

  public lineChartData: Array<any> = [];
  public getPersonalityDetails(): [string, string] {
    return ["ABCD", ' <p class="text-primary"> Description of ABCD type </p>'];
  }
  public getPersonalityDetailsOfPartner(): [string, string, string] {
    // Tuple(name, typecode, innerHTML)
    return ["partner Name ", "XYZ", "<p>Description of XYZ</p>"];
  }
}
