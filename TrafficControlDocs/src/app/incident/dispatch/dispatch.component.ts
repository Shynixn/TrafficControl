import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {StaffMemberService} from "../../service/staff-member.service";
import {max} from "rxjs/operators";
import {IncidentService} from "../../service/incident.service";

@Component({
  selector: 'app-dispatch',
  templateUrl: './dispatch.component.html',
  styleUrls: ['./dispatch.component.sass']
})
export class DispatchComponent implements OnInit {

  public staffMembers: object[];
  private _maxId = 0;

  public type: string = "Accident";
  public priority: string = "Low";
  public startDay: number = 1;
  public startMonth: number = 1;
  public startYear: number = 2019;
  public endDay: number = 1;
  public endMonth: number = 12;
  public endYear: number = 2019;
  public status: string = "Ready";
  public assigned: string = "";

  @Output() reloadIncidents = new EventEmitter();


  @Input() set maxId(maxId: number){
    this._maxId = maxId+1;
  }

  get maxId(): number{
    return this._maxId;
  }

  constructor(
    private staffMemberService: StaffMemberService,
    private incidentService: IncidentService
  ) { }

  ngOnInit() {
    this.staffMemberService.retrieveStaffMembers()
      .then(value => {
        this.staffMembers = value;
        console.log(value);
      });
  }

  dispatchIncident() {
    this.incidentService.sendIncident({
      type: this.type,
      priority: this.priority,
      startDay: this.startDay,
      startMonth: this.startMonth,
      startYear: this.startYear,
      endDay: this.endDay,
      endMonth: this.endMonth,
      endYear: this.endYear,
      status: this.status,
      assigned: this.assigned
    }).then(
      (value => this.incidentDispatched(value))
    );
  }

  private incidentDispatched(value: any) {
    console.log(value);
    this.reloadIncidents.emit();
  }
}

