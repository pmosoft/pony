import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CloneSpringComponent } from './clone-spring.component';

describe('CloneSpringComponent', () => {
  let component: CloneSpringComponent;
  let fixture: ComponentFixture<CloneSpringComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CloneSpringComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CloneSpringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
