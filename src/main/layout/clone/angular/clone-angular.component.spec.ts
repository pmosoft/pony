import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CloneAngularComponent } from './clone-angular.component';

describe('CloneAngularComponent', () => {
  let component: CloneAngularComponent;
  let fixture: ComponentFixture<CloneAngularComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CloneAngularComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CloneAngularComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
