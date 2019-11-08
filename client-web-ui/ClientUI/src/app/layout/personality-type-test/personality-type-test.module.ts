import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { PersonalityTypeTestComponent } from "./personality-type-test.component";
import { PersonalityTypeTestRoutingModule } from "./personality-type-test-routing.module";

@NgModule({
  imports: [CommonModule, PersonalityTypeTestRoutingModule, PageHeaderModule],
  declarations: [PersonalityTypeTestComponent]
})
export class PersonalityTypeTestModule {}
