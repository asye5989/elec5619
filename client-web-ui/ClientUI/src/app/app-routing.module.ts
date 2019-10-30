import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { DashComponent } from "./dash/dash.component";
import { AuthGaurdService } from "./service/auth-guard.service";
import { HomeComponent } from "./home/home.component";
import { MbtiComponent } from "./mbti/mbti.component";
import { LogoutComponent } from "./logout/logout.component";
import { LoginComponent } from "./login/login.component";

const routes: Routes = [
  { path: "", component: DashComponent, canActivate: [AuthGaurdService] },
  { path: "home", component: HomeComponent, canActivate: [AuthGaurdService] },
  { path: "mbti", component: MbtiComponent, canActivate: [AuthGaurdService] },

  {
    path: "logout",
    component: LogoutComponent,
    canActivate: [AuthGaurdService]
  },
  { path: "login", component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
