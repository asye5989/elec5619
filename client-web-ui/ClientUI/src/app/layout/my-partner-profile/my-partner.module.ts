import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { MyPartnerComponent } from "./my-partner.component";
import { MyPartnerRoutingModule } from "./my-partner-routing.module";

@NgModule({
  imports: [CommonModule, MyPartnerRoutingModule, PageHeaderModule],
  declarations: [MyPartnerComponent]
})
export class MyPartnerModule {}
