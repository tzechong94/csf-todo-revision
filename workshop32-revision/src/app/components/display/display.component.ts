import { Component, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Subject } from 'rxjs';
import { EditedTask, Task } from 'src/app/models';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnChanges{

  @Input()
  tasks: Task[] = []

  @Output()
  onEditTask = new Subject<string>

  @Output()
  onRemoveTask = new Subject<string>

  removeTask(taskId: string) {
    // this.tasks.splice(idx,1)
    this.onRemoveTask.next(taskId)

  }

  editTask(taskId: string) {
    this.onEditTask.next(taskId)
  }

  ngOnChanges(changes: SimpleChanges): void {
      console.log(changes, 'changes')
      this.tasks = changes['tasks'].currentValue
      console.log('updated tasks' , this.tasks)
  }

}
