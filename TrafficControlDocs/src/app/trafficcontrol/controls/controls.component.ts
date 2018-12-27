import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'trafficcontrol-controls',
  templateUrl: './controls.component.html',
  styleUrls: ['./controls.component.sass']
})
export class ControlsComponent implements OnInit {
  subsystemsOnline = [true, false, true, false];
  streetNames = ["Linzer Straße", "Johann-Wilhelm-Klein Straße", "Tranquility Lane", "Lil' Marco"];
  blockSelection = [100, 50, 25, 0];
  trafficLight = ["green", "red"];

  constructor() { }

  ngOnInit() {
  }

}
