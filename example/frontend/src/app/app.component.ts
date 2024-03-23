import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { OAuthService } from 'angular-oauth2-oidc';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  helloText = '';

  constructor(private oauthService: OAuthService,  private httpClient: HttpClient) {

  }
  logout() {
    this.oauthService.logOut();
  }

  getHelloText() {
    this.httpClient.get<{ message: string }>('http://localhost:8081/hello', {
      headers: {
        'Authorization': `Bearer ${this.oauthService.getAccessToken()}`
      }
    }).subscribe(
      data => this.helloText = data.message,
      error => console.log('oops', error)
    );
  }
}
