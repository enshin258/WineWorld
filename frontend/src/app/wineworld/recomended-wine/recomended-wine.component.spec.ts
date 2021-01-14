import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecomendedWineComponent } from './recomended-wine.component';

describe('RecomendedWineComponent', () => {
  let component: RecomendedWineComponent;
  let fixture: ComponentFixture<RecomendedWineComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecomendedWineComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecomendedWineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
