import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { PersonalityTypeTestComponent } from "./personality-type-test.component";

const routes: Routes = [
  {
    path: "",
    component: PersonalityTypeTestComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonalityTypeTestRoutingModule {}
