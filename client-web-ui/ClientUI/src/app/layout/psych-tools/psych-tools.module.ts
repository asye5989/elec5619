import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { PsychToolsComponent } from "./psych-tools.component";
import { PsychToolsRoutingModule } from "./psych-tools-routing.module";

@NgModule({
  imports: [CommonModule, PsychToolsRoutingModule, PageHeaderModule],
  declarations: [PsychToolsComponent]
})
export class PsychToolsModule {}
