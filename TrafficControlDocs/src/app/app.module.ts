import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { DocumentationComponent } from './documentation/documentation.component';
import { DocumentationNavComponent } from './documentation-nav/documentation-nav.component';
import { IntelijConfigurationsComponent } from './documentation/intelij-configurations/intelij-configurations.component';
import { TrafficcontrolComponent } from './trafficcontrol/trafficcontrol.component';
import { ControlsComponent } from './trafficcontrol/controls/controls.component';
import { RenderComponent } from './trafficcontrol/render/render.component';
import { FormsModule } from '@angular/forms';
import {LoginService} from "./service/login.service";
import {HttpClientModule} from "@angular/common/http";
import {CityService} from "./service/city.service";

@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    DocumentationComponent,
    DocumentationNavComponent,
    IntelijConfigurationsComponent,
    TrafficcontrolComponent,
    ControlsComponent,
    RenderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    LoginService,
    CityService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
