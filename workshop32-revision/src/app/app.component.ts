import { Component, OnInit } from '@angular/core';
import { switchMap } from 'rxjs';
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
  currentId!: string

  newTaskData(task: Task) {
    this.tasks.push(task)
  }

  editingTask(taskId: string) {
    this.taskSvc.getTaskById(taskId).subscribe(data => this.selectedTask = data) // getTaskById
    this.currentId = taskId 
  }

  editedTaskData(task: Task) {
    // this.tasks[this.currentIndex] = task
    console.log('task after clicking edit ', task)
    this.taskSvc.updateTaskById(this.currentId, task).pipe(
      switchMap(()=>  this.taskSvc.getTasks())
    ).subscribe((data: Task[]) => this.tasks = data)
  }

  removeTask(taskId: string) {
    this.taskSvc.deleteTask(taskId).pipe(
      switchMap(()=> this.taskSvc.getTasks())
    ).subscribe((data: Task[]) => this.tasks = data)
  }

  ngOnInit(): void {
      this.taskSvc.getTasks().subscribe(
        (data: Task[]) => this.tasks = data
      )
  }
}
