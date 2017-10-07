import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {TableElementsCountComponent} from './table-elements-count/table-elements-count.component';
import {TablePaginationComponent} from './table-pagination/table-pagination.component';
import {TableSortComponent} from './table-sort/table-sort.component';
import {TodoService} from './todo.service';
import { TodoComponent } from './todo/todo.component';
import { TodoListComponent } from './todo-list/todo-list.component'

const appRoutes: Routes = [
    {path: '', component: TodoListComponent},
    {path: 'todo/:id', component: TodoComponent, resolve: {todo: TodoService}}
];

@NgModule({
    declarations: [
        AppComponent,
        TableElementsCountComponent,
        TablePaginationComponent,
        TableSortComponent,
        TodoComponent,
        TodoListComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        RouterModule.forRoot(appRoutes)
    ],
    providers: [TodoService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
