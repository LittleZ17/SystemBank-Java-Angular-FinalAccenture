import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FileStoreComponent } from './file-store.component';

describe('FileStoreComponent', () => {
  let component: FileStoreComponent;
  let fixture: ComponentFixture<FileStoreComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FileStoreComponent]
    });
    fixture = TestBed.createComponent(FileStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
