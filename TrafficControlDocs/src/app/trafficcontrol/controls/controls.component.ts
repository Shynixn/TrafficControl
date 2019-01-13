import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LoginService} from "../../service/login.service";
import {CityService} from "../../service/city.service";

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

  public username = "master-user";
  public password = "horse-staple";
  public isLoggedIn = false;

  @Output() city = new EventEmitter<any>();

  constructor(
    private loginService: LoginService,
    private cityService: CityService
  ) {
  }

  ngOnInit() {
  }

  public login(){
    this.loginService.authenticate(
      this.username, this.password
    ).then(
      value => {
        this.isLoggedIn = true;

        this.cityService.requestCity(this.username, this.password)
          .then(
            street => {
              console.log(street);
              this.city.emit(street);
            }
          ).catch(
            error => console.log(error)
        );
      }
    ).catch(
      error => {
        this.isLoggedIn = false;
      }
    )
  }
}
