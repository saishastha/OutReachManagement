import { Component, OnInit, Inject, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from './services/login.service';
import { takeWhile, finalize } from 'rxjs/operators';
import { LOCAL_STORAGE, WebStorageService } from 'angular-webstorage-service';
import * as _ from 'lodash';
// import { LocalStorageService } from './../../shared/local-storage-service.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  canSubscribe: boolean;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private changeDetector: ChangeDetectorRef,
    // private alertService: AlertService
  ) {
    // redirect to home if already logged in
    // if (this.authenticationService.currentUserValue) {
    //   this.router.navigate(['/']);
    // }
  }

  ngOnInit() {
    if (localStorage.getItem('Role')) {
      localStorage.clearItem('Role');
    }
    this.loginForm = new FormGroup({
      emailaddress: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      checkbox: new FormControl()
    });

    // get return url from route parameters or default to '/'
    // this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    this.canSubscribe = true;
  }


  // convenience getter for easy access to form fields
  // get f() { return this.loginForm.controls; }

  get emailaddress() {
    return this.loginForm.get('emailaddress');
  }
  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {

    this.submitted = true;
    this.loading = true
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    const request = {
      email: this.emailaddress.value,
      password: this.password.value
    }
    debugger;
    this.loginService.getUserCredentials(request)
      .pipe(takeWhile(() => this.canSubscribe),
        finalize(() => {
          this.changeDetector.detectChanges();
        })).subscribe((data: any) => {
          if (data) {
            debugger;
            // const user = _.filter(data, { 'Email': emailAddress, 'Password': password });

            if (data.status === 200 && data.role) {

              this.loading = false;
              alert('Login successful !!!');
              if (data.role === 'Admin') {
                this.router.navigate(['/dashboard']);
                this.saveInLocal('Role', 'Admin');
              } else if (data.role === 'Pmo') {
                this.router.navigate(['/dashboard']);
                this.saveInLocal('Role', 'Pmo');
              } else if (data.role === 'Poc') {
                this.router.navigate(['/dashboard']);
                // this.router.navigate(['/dashboard', { role: getUser['Role'] }]);
                this.saveInLocal('Role', 'Poc');
              } else if (data.role === 'Participant') {
                this.saveInLocal('Role', 'Participant');
              } else {
                alert('No Role configured for you.!!!');
              }
            } else {
              alert('Login Unsuccessfull !!!');
            }

          }
        },
          (error: Error) => {

          });

  }
  saveInLocal(key, val): void {
    console.log('recieved= key:' + key + 'value:' + val);
    // this.localStorageService.setItemByKey(key, val, true);
    localStorage.setItem(key, val);
  }

}
