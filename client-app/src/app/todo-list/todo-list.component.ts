import {Component, OnInit} from '@angular/core';
import {Response} from '@angular/http';
import {Router} from '@angular/router';
import * as Rx from 'rxjs/Rx';

import 'rxjs/add/operator/switchMap';

import {PaginationPage, PaginationPropertySort} from '../pagination';
import {Table} from '../table';
import {showLoading, hideLoading, doNothing} from '../commons'
import {TodoService} from '../todo.service';
import {Todo} from '../domain';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit, Table<Todo> {
  
      todoPage: PaginationPage<Todo>;
      self: Table<Todo>;
      text : string; 
      
      constructor(private todoService: TodoService, private router: Router) {
  
      }
  
      ngOnInit() {
          let observable: Rx.Observable<PaginationPage<any>> = this.fetchPage(0, 10, null);
          showLoading();
          observable.subscribe(doNothing, hideLoading, hideLoading);
          this.self = this;
      }
  
      fetchPage(pageNumber: number, pageSize: number, sort: PaginationPropertySort): Rx.Observable<PaginationPage<Todo>> {
          let observable: Rx.Observable<PaginationPage<Todo>> = this.todoService.findTodo(pageNumber, pageSize, sort);
          observable.subscribe(todoPage => this.todoPage = todoPage);
          return observable;
      }
  
      goToDetails(todo) {
          this.router.navigate(['todo', todo.id]);
      }
  
      add(){
        let observable: Rx.Observable<Response> = this.todoService.addTodo(this.text);
        showLoading();
        observable.switchMap(() => {
            return this.fetchPage(0, 10, null);
        }).subscribe(doNothing, hideLoading, hideLoading);
      }
      markCompleted(todo){
        let observable: Rx.Observable<Response> = this.todoService.markCompleted(todo.id);
        showLoading();
        observable.switchMap(() => {
            return this.fetchPage(0, 10, null);
        }).subscribe(doNothing, hideLoading, hideLoading);
      }
      delete(todo) {
  
          let observable: Rx.Observable<Response> = this.todoService.deleteTodo(todo.id);
          showLoading();
          observable.switchMap(() => {
              return this.fetchPage(0, 10, null);
          }).subscribe(doNothing, hideLoading, hideLoading);
      }
  }