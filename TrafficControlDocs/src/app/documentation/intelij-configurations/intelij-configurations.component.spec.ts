import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IntelijConfigurationsComponent } from './intelij-configurations.component';

describe('IntelijConfigurationsComponent', () => {
  let component: IntelijConfigurationsComponent;
  let fixture: ComponentFixture<IntelijConfigurationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IntelijConfigurationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IntelijConfigurationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
