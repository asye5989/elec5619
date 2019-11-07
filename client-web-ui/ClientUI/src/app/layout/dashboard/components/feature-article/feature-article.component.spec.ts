import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FeatureArticleComponent } from './feature-article.component';

describe('FeatureArticleComponent', () => {
  let component: FeatureArticleComponent;
  let fixture: ComponentFixture<FeatureArticleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FeatureArticleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FeatureArticleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
