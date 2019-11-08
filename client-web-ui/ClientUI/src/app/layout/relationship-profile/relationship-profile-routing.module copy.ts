import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { RelationshipProfileComponent } from "./relationship-profile.component";

const routes: Routes = [
  {
    path: "",
    component: RelationshipProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RelationshipProfileRoutingModule {}
