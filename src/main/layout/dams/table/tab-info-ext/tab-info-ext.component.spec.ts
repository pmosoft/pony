import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabInfoExtComponent } from './tab-info-ext.component';

describe('TabInfoExtComponent', () => {
  let component: TabInfoExtComponent;
  let fixture: ComponentFixture<TabInfoExtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabInfoExtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabInfoExtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
