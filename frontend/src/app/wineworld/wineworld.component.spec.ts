import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WineworldComponent } from './wineworld.component';

describe('WineworldComponent', () => {
  let component: WineworldComponent;
  let fixture: ComponentFixture<WineworldComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WineworldComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WineworldComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
