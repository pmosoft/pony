import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JdbcInfoComponent } from './jdbc-info.component';

describe('JdbcInfoComponent', () => {
  let component: JdbcInfoComponent;
  let fixture: ComponentFixture<JdbcInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JdbcInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JdbcInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
