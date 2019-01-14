import {Component, OnInit, ViewChild} from '@angular/core';
import {DetailsComponent} from "./details/details.component";

@Component({
  selector: 'app-incident',
  templateUrl: './incident.component.html',
  styleUrls: ['./incident.component.sass']
})
export class IncidentComponent implements OnInit {
  public maxId = 1;

  @ViewChild(DetailsComponent) child;

  constructor() { }

  ngOnInit() {
  }

  onMaxId($event) {
    this.maxId = $event;
  }

  onReloadIncidents() {
    this.child.loadIncidents();
  }
}
