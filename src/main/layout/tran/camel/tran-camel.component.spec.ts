import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TranCamelComponent } from './tran-camel.component';

describe('TranCamelComponent', () => {
  let component: TranCamelComponent;
  let fixture: ComponentFixture<TranCamelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TranCamelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TranCamelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
