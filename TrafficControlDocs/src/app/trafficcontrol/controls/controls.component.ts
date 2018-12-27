import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'trafficcontrol-controls',
  templateUrl: './controls.component.html',
  styleUrls: ['./controls.component.sass']
})
export class ControlsComponent implements OnInit {
  public subsystemsOnline = [true, false, true, false];
  public streetNames = ["Linzer Straße", "Johann-Wilhelm-Klein Straße", "Tranquility Lane", "Lil' Marco"];
  public blockSelection = [100, 50, 25, 0];
  public trafficLight = ["green", "red"];

  constructor() { }

  ngOnInit() {
  }

}
