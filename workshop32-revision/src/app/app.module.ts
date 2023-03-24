import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { InputComponent } from './components/input/input.component';
import { DisplayComponent } from './components/display/display.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import {MatToolbarModule} from '@angular/material/toolbar';
import { HttpClientModule } from '@angular/common/http';
import { TaskService } from './task.service';


@NgModule({
  declarations: [
    AppComponent,
    InputComponent,
    DisplayComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, ReactiveFormsModule, MatButtonModule, BrowserAnimationsModule,
    MatCardModule,MatInputModule,MatDatepickerModule, MatNativeDateModule, MatRadioModule,
    MatToolbarModule  
  ],
  providers: [    
    MatDatepickerModule,
    MatNativeDateModule,
    TaskService  
],
  bootstrap: [AppComponent]
})
export class AppModule { }
