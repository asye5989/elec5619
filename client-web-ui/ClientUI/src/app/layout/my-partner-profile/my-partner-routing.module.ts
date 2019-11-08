import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { MyPartnerComponent } from "./my-partner.component";

const routes: Routes = [
  {
    path: "",
    component: MyPartnerComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MyPartnerRoutingModule {}
