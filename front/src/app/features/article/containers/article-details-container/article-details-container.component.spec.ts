import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleDetailsContainerComponent } from './article-details-container.component';

describe('ArticleDetailsContainerComponent', () => {
  let component: ArticleDetailsContainerComponent;
  let fixture: ComponentFixture<ArticleDetailsContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticleDetailsContainerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleDetailsContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
