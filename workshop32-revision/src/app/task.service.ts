import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, throwError } from "rxjs";
import { Task } from "./models";


const GET_ALL_URL = 'http://localhost:8080/api/tasks'
const ADD_TASK_URL = 'http://localhost:8080/api/task'

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
    })
  };
  

@Injectable()
export class TaskService {
    removeTaskById(taskId: string) {
      throw new Error('Method not implemented.');
    }


    constructor(private http: HttpClient){}

    getTaskById(taskId: string) {
      const url = `${ADD_TASK_URL}/${taskId}` 
      return this.http.get<Task>(url)
    }

    getTasks() {
        return this.http.get<Task[]>(GET_ALL_URL)
    }

    addTask(task: Task): Observable<Task> {
        return this.http.post<Task>(ADD_TASK_URL, task, httpOptions)
            .pipe(
                catchError(this.handleError)
            )
    }

    updateTaskById(id: string, task: Task) {
        const url = `${ADD_TASK_URL}/${id}` 
        return this.http.put<Task>(url, task, httpOptions)
            .pipe(
                catchError(this.handleError)
            )
    }

    deleteTask(id:string): Observable<unknown> {
        const url = `${ADD_TASK_URL}/${id}` 
        return this.http.delete(url, httpOptions)
            .pipe(
                catchError(this.handleError)
            )
    }

    private handleError(error: HttpErrorResponse) {
        if (error.status === 0) {
          // A client-side or network error occurred. Handle it accordingly.
          console.error('An error occurred:', error.error);
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong.
          console.error(
            `Backend returned code ${error.status}, body was: `, error.error);
        }
        // Return an observable with a user-facing error message.
        return throwError(() => new Error('Something bad happened; please try again later.'));
      }
      
}