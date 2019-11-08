import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { RelationshipProfileComponent } from "./relationship-profile.component";
import { RelationshipProfileRoutingModule } from "./relationship-profile-routing.module";

@NgModule({
  imports: [CommonModule, RelationshipProfileRoutingModule, PageHeaderModule],
  declarations: [RelationshipProfileComponent]
})
export class RelationshipProfileModule {}
