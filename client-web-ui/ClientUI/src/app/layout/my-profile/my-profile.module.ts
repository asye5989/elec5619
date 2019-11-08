import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { PageHeaderModule } from "../../shared";
import { MyProfileComponent } from "./my-profile.component";
import { MyProfileRoutingModule } from "./my-profile-routing.module";

@NgModule({
  imports: [CommonModule, MyProfileRoutingModule, PageHeaderModule],
  declarations: [MyProfileComponent]
})
export class MyProfileModule {}
