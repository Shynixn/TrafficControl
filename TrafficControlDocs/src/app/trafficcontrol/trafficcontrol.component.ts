import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trafficcontrol',
  templateUrl: './trafficcontrol.component.html',
  styleUrls: ['./trafficcontrol.component.sass']
})
export class TrafficcontrolComponent implements OnInit {
  username : String;
  password :String;

  tmpUsername : String;
  tmpPassword : String;

  constructor() { }

  ngOnInit() {
  }
}
