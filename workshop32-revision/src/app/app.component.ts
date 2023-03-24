import { Component, OnInit } from '@angular/core';
import { EditedTask, Task } from './models';
import { TaskService } from './task.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'workshop32-revision';

  constructor(private taskSvc: TaskService) {}

  tasks: Task[] = []

  selectedTask!: Task
  currentIndex!: number

  newTaskData(task: Task) {
    this.tasks.push(task)
  }

  editingTask(idx: number) {
    this.selectedTask = this.tasks[idx]
    this.currentIndex = idx
  }

  editedTaskData(task: Task) {
    this.tasks[this.currentIndex] = task
  }

  ngOnInit(): void {
      this.taskSvc.getTasks().subscribe(
        (data: Task[]) => this.tasks = data
      )
  }
}
