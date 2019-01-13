import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../service/login.service";

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

  constructor(
    private loginService: LoginService
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
      }
    ).catch(
      error => {
        this.isLoggedIn = false;
      }
    )
  }
}
