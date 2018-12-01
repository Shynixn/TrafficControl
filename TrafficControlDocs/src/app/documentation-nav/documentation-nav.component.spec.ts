import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentationNavComponent } from './documentation-nav.component';

describe('DocumentationNavComponent', () => {
  let component: DocumentationNavComponent;
  let fixture: ComponentFixture<DocumentationNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentationNavComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentationNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
