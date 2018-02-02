import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {UserLoginService} from "../../../service/user-login.service";
import {CognitoCallback, LoggedInCallback} from "../../../service/cognito.service";
import {DynamoDBService} from "../../../service/ddb.service";


@Component({
    selector: 'awscognito-angular2-app',
    templateUrl: './login.html'
})
export class LoginComponent implements CognitoCallback, LoggedInCallback, OnInit {
    email: string;
    password: string;
    role: string;
    errorMessage: string;
    private sub: any;

    constructor(public router: Router,
                public ddb: DynamoDBService,
                public route: ActivatedRoute,
                public userService: UserLoginService) {
        console.log("LoginComponent constructor");
    }

    ngOnInit() {
        this.errorMessage = null;
        this.sub = this.route.params.subscribe(params => {
            this.role = params['role'];
        });
        console.log("Checking if the user is already authenticated. If so, then redirect to the secure site");
        this.userService.isAuthenticated(this.role, this);
      }

    onLogin() {
        if (this.email == null || this.password == null) {
            this.errorMessage = "All fields are required";
            return;
        }
        this.errorMessage = null;
        this.userService.authenticate(this.email, this.password, this.role, this);
    }

    cognitoCallback(message: string, result: any) {
        if (message != null) { //error
            this.errorMessage = message;
            console.log("result: " + this.errorMessage);
            if (this.errorMessage === 'User is not confirmed.') {
                console.log("redirecting");
                this.router.navigate(['/home/confirmRegistration', this.email]);
            } else if (this.errorMessage === 'User needs to set password.') {
                console.log("redirecting to set new password");
                this.router.navigate(['/home/newPassword']);
            }
        } else { //success
            this.ddb.writeLogEntry("login");
            this.router.navigate(['/securehome']);
        }
    }

    isLoggedIn(message: string, isLoggedIn: boolean) {
        if (isLoggedIn)
            this.router.navigate(['/securehome']);
    }
}