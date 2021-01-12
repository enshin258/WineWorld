import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryOfWineComponent } from './history-of-wine.component';

describe('HistoryOfWineComponent', () => {
  let component: HistoryOfWineComponent;
  let fixture: ComponentFixture<HistoryOfWineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryOfWineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryOfWineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
