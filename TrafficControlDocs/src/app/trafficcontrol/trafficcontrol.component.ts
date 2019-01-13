import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trafficcontrol',
  templateUrl: './trafficcontrol.component.html',
  styleUrls: ['./trafficcontrol.component.sass']
})
export class TrafficcontrolComponent implements OnInit {
  city: any = null;

  constructor() { }

  ngOnInit() {
  }

  onCityLoaded($event: any) {
    this.city = $event;
  }
}
