import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { PsychToolsComponent } from "./psych-tools.component";

const routes: Routes = [
  {
    path: "",
    component: PsychToolsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PsychToolsRoutingModule {}
