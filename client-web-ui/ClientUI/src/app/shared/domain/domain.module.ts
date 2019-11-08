import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { User } from "./user";
import { Match } from "./match";
import { MbtiQuestion } from "./mbti-question";

@NgModule({
  declarations: [],
  providers: [MbtiQuestion, Match, , User],
  imports: [CommonModule]
})
export class DomainModule {}
