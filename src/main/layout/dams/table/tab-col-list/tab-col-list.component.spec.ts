import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabColListComponent } from './tab-col-list.component';

describe('TabColListComponent', () => {
  let component: TabColListComponent;
  let fixture: ComponentFixture<TabColListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabColListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabColListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
