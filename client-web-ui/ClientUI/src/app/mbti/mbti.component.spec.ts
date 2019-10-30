import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MbtiComponent } from './mbti.component';

describe('MbtiComponent', () => {
  let component: MbtiComponent;
  let fixture: ComponentFixture<MbtiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MbtiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MbtiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
