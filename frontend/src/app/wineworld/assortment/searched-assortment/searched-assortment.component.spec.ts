import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchedAssortmentComponent } from './searched-assortment.component';

describe('SearchedAssortmentComponent', () => {
  let component: SearchedAssortmentComponent;
  let fixture: ComponentFixture<SearchedAssortmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchedAssortmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchedAssortmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
