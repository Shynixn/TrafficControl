import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {IncidentService} from "../../service/incident.service";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-incident-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.sass']
})
export class DetailsComponent implements OnInit {
  public incidents: object[] = [];
  @Output() public maxId = new EventEmitter<number>();

  constructor(
    private incidentService: IncidentService
  ) { }

  ngOnInit() {
    this.loadIncidents();
  }

  public loadIncidents(){
    this.incidentService.requestIncidents()
      .then(
        value => {
          let tmpMaxId = 0;
          this.incidents = value;
          this.incidents.forEach( incident => {
            if(incident["id"] > tmpMaxId){
              tmpMaxId = incident["id"];
            }
          });
          this.incidents.sort((a,b) => a["id"] - b["id"]);

          this.maxId.emit(tmpMaxId);
          console.log(value);
        }
      );
  }

}
