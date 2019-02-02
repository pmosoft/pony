import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DelimeterComponent } from './delimeter.component';

describe('DelimeterComponent', () => {
  let component: DelimeterComponent;
  let fixture: ComponentFixture<DelimeterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DelimeterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DelimeterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
