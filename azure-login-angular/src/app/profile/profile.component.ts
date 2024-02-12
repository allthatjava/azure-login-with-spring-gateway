import { Component, Input, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MsalService } from '@azure/msal-angular';
import { InteractionRequiredAuthError } from '@azure/msal-browser';

const GRAPH_ENDPOINT = 'https://graph.microsoft.com/v1.0/me'; // Prod graph endpoint. Uncomment to use.
//const GRAPH_ENDPOINT = 'https://graph.microsoft-ppe.com/v1.0/me';

type ProfileType = {
  givenName?: string,
  surname?: string,
  userPrincipalName?: string,
  id?: string
};

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile!: ProfileType;
 accessToken:String = '';
 heroes:String ="";
 vigilante:String = "";

  constructor(
    private http: HttpClient,
    private authService: MsalService
  ) { }

  ngOnInit() {
    this.getProfile();
  }

  getProfile() {

    var request = {
      //scopes: ["User.Read"],
      // scopes: ["56e17b97-d97d-4842-a250-343f604a259c/.default"]      
      // scopes: ["https://graph.microsoft.com/.default"]
      scopes: ["56e17b97-d97d-4842-a250-343f604a259c/.default"]
    };

    this.authService.instance.acquireTokenSilent(request).then(tokenResponse => {
      this.accessToken = tokenResponse.accessToken;
      // this.accessToken = tokenResponse.idToken;

      const headerDict = {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer '+this.accessToken
      }

      const headerDict_without_authrization = {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
        'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    
      const requestOptions = {                                                                                                                                                                                 
        headers: new HttpHeaders(headerDict),
      };

      const requestOptions_without_authrization = {                                                                                                                                                                                 
        headers: new HttpHeaders(headerDict_without_authrization),
      };
    
        console.log("Access_token:"+this.accessToken );
    
        // this.http.get(GRAPH_ENDPOINT, requestOptions)
        //   .subscribe(profile => {
        //     this.profile = profile;
        //   });


        this.http.get("http://localhost:8080/api/heroes",requestOptions_without_authrization)
        .subscribe(heroes => {
          this.heroes = JSON.stringify(heroes);
          console.log("No login required page:"+heroes.toString());
        });

        this.http.get("http://localhost:8080/api/vigilante/1",requestOptions)
        .subscribe(vigilante => {
          this.vigilante = JSON.stringify(vigilante);
          console.log("Login required page:"+vigilante.toString());
        }); 

        // this.http.get("http://localhost:8080/token_details", requestOptions)
        // .subscribe(heroes => {
        //   console.log("Login required:"+heroes);
        // });
        // this.http.get("http://localhost:9999/graph", requestOptions)
        // .subscribe(heroes => {
        //   console.log("Login required:"+heroes);
        // });

    }).catch(async (error) => {
      // if (error instanceof InteractionRequiredAuthError) {
      //     // fallback to interaction when silent call fails
      //     return this.authService.acquireTokenPopup(request);
      // }
      // handle other errors
  })

  var requestGraph = {
    scopes: ["User.Read"],
    // scopes: ["56e17b97-d97d-4842-a250-343f604a259c/.default"]      
    // scopes: ["https://graph.microsoft.com/.default"]
    // scopes: ["56e17b97-d97d-4842-a250-343f604a259c/.default"]
  };

  this.authService.instance.acquireTokenSilent(requestGraph).then(tokenResponse => {
    this.accessToken = tokenResponse.accessToken;

    const requestOptions = {                                                                                                                                                                                 
      headers: new HttpHeaders(
        {
          'Access-Control-Allow-Origin': '*',
          'Authorization': 'Bearer '+this.accessToken
        }
        ),
    };

    this.http.get(GRAPH_ENDPOINT, requestOptions)
    .subscribe(profile => {
          this.profile = profile;
          console.log("Profile:"+this.profile);
        });
  }).catch(async (error)=>{
  })

  }
}