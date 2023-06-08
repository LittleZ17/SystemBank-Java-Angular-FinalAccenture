import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FilestorageService } from 'src/app/services/filestorage.service';

@Component({
  selector: 'app-file-store',
  templateUrl: './file-store.component.html',
  styleUrls: ['./file-store.component.css']
})
export class FileStoreComponent implements OnInit{

  infoData: any;

  uploadForm: FormGroup;
  showForm: boolean = false; 
  showImage: boolean = false;

  selectedFile: File | null = null;
 
  constructor(private formBuilder: FormBuilder, private fileService: FilestorageService) {
    this.uploadForm = this.formBuilder.group({
      archivo: [null]
    });
  }
  ngOnInit(): void {
  }


  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0] as File;
  }

  onUpload(): void {
    if (this.selectedFile) {
      const formData = new FormData();
      formData.append('file', this.selectedFile, this.selectedFile.name);

      this.fileService.postFile(formData).subscribe({
        next:(data) => {
          console.log(data);
          this.infoData = data.url;
          this.showImage = true; 

        }
      }
      );
    }
  }

  onSubmit(): void {
    if (this.uploadForm.invalid) {
      return;
    }
    this.onUpload();
  }
  toggleForm() {
    this.showForm = !this.showForm;
  }

}
