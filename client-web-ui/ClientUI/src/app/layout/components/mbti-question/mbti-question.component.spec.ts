import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MbtiQuestionComponent } from './mbti-question.component';

describe('MbtiQuestionComponent', () => {
  let component: MbtiQuestionComponent;
  let fixture: ComponentFixture<MbtiQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MbtiQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MbtiQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
