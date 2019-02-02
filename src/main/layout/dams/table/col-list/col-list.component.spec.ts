import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ColListComponent } from './col-list.component';

describe('ColListComponent', () => {
  let component: ColListComponent;
  let fixture: ComponentFixture<ColListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ColListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ColListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
