import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrafficcontrolComponent } from './trafficcontrol.component';

describe('TrafficcontrolComponent', () => {
  let component: TrafficcontrolComponent;
  let fixture: ComponentFixture<TrafficcontrolComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrafficcontrolComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrafficcontrolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
