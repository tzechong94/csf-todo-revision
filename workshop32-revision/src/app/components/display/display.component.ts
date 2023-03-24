import { Component, Input, OnChanges, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { EditedTask, Task } from 'src/app/models';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent {

  @Input()
  tasks: Task[] = []

  @Output()
  onEditTask = new Subject<number>

  removeTask(idx: number) {
    this.tasks.splice(idx,1)
  }

  editTask(idx: number) {
    this.onEditTask.next(idx)

  }

}
