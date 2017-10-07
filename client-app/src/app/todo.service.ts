import {Injectable} from '@angular/core';
import {Todo} from './domain'
import {PaginationPage, PaginationPropertySort} from './pagination';
import {webServiceEndpoint} from './commons';
import {Http, Response, URLSearchParams, RequestOptions} from '@angular/http';
import {Resolve, RouterStateSnapshot, ActivatedRouteSnapshot} from '@angular/router';
import {Headers} from '@angular/http'
import * as Rx from "rxjs/Rx";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/publish';

@Injectable()
export class TodoService implements Resolve<Todo>{
   // headers: Headers;
   // options: RequestOptions;

    constructor(private http: Http) {
       // this.headers = new Headers({ 'Content-Type': 'application/json', 
       // 'Accept': 'q=0.8;application/json;q=0.9' });
       // this.options = new RequestOptions({ headers: this.headers });
    }

    findTodo(page: number, pageSize: number, sort: PaginationPropertySort): Rx.Observable<PaginationPage<Todo>> {
        let params = new URLSearchParams();
        params.set('size', `${pageSize}`);
        params.set('page', `${page}`);
        if (sort != null) {
            params.set('sort', `${sort.property},${sort.direction}`);
        }

        let options = new RequestOptions({
            search: params
        });
        return this.http.get(`${webServiceEndpoint}/todo`, options).map(this.extractData).publish().refCount();
    }

    getTodo(id: string): Rx.Observable<Todo> {
        return this.http.get(`${webServiceEndpoint}/todo/${id}`).map(this.extractData).publish().refCount();
    }

    resolve(route: ActivatedRouteSnapshot,state: RouterStateSnapshot): Rx.Observable<Todo> {
        return this.getTodo(route.params['id']);
    }

    addTodo(text:string): Rx.Observable<Response> {
        return this.http.post(`${webServiceEndpoint}/todo`,text).publish().refCount();
    }

    markCompleted(id:string): Rx.Observable<Response> {
      
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put(`${webServiceEndpoint}/todo/${id}`,2,{headers: headers}).publish().refCount();
    }

    deleteTodo(id: string): Rx.Observable<Response> {
        return this.http.delete(`${webServiceEndpoint}/todo/${id}`).publish().refCount();
    }

    private extractData(res: Response) {
        let body = res.json();
        return body || {};
    }
}
