import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { RelationshipProfileComponent } from "./relationship-profile.component";
import { RelationshipProfileRoutingModule } from "./relationship-profile-routing.module";

import { ChartsModule as Ng2Charts } from "ng2-charts";

@NgModule({
  imports: [
    CommonModule,
    RelationshipProfileRoutingModule,
    Ng2Charts,
    PageHeaderModule
  ],
  declarations: [RelationshipProfileComponent]
})
export class RelationshipProfileModule {}
