import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { PersonalityTypeTestComponent } from "./personality-type-test.component";
import { PersonalityTypeTestRoutingModule } from "./personality-type-test-routing.module";
import { FormsModule } from "@angular/forms";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PersonalityTypeTestRoutingModule,
    PageHeaderModule
  ],
  declarations: [PersonalityTypeTestComponent]
})
export class PersonalityTypeTestModule {}
