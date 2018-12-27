import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {WelcomeComponent} from './welcome/welcome.component';
import {DocumentationComponent} from './documentation/documentation.component';
import {DocumentationNavComponent} from "./documentation-nav/documentation-nav.component";
import {IntelijConfigurationsComponent} from "./documentation/intelij-configurations/intelij-configurations.component";
import {TrafficcontrolComponent} from "./trafficcontrol/trafficcontrol.component";

const routes: Routes = [
  {
    path: '',
    component: WelcomeComponent
  },
  {
    path: 'home',
    component: WelcomeComponent
  },
  {
    path: 'docs',
    component: DocumentationNavComponent
  },
  {
    path: 'docs/build',
    component: DocumentationComponent
  },
  {
    path: 'docs/intelij_configurations',
    component: IntelijConfigurationsComponent
  },
  {
    path: 'trafficcontrol',
    component: TrafficcontrolComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
