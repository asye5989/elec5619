import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { JwtInterceptor } from "./JwtInterceptor";

@NgModule({
  declarations: [],
  providers: [JwtInterceptor],
  imports: [CommonModule]
})
export class InterceptorsModule {}
