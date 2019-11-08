import { CommonModule } from "@angular/common";
import { HttpClientModule, HTTP_INTERCEPTORS } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { LanguageTranslationModule } from "./shared/modules/language-translation/language-translation.module";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { AuthGuard } from "./shared";
import { User } from "./shared/domain/user";
import { JwtInterceptor } from "./shared/interceptors/JwtInterceptor";

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    LanguageTranslationModule,
    AppRoutingModule
  ],
  declarations: [AppComponent],
  providers: [
    AuthGuard,
    User,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
