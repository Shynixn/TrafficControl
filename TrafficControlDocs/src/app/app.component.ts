import {Component, Inject} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import {environment} from "../environments/environment";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent {
  constructor(@Inject(DOCUMENT) private document){
    let base = this.document.getElementsByTagName('base');

    if(base.length > 0){
      base[0].setAttribute('href', environment.baseHref)
    }
  }
}
