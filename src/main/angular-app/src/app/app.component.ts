import {Component} from '@angular/core';
import {Person, PersonControllerService} from "../generated";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(private personControllerService: PersonControllerService) {
    this.fetchPerson()
  }

  title = 'angular-app';
  person: Person | null | undefined

  fetchPerson() {
    this.personControllerService.personById(1).subscribe((response) => {
        this.person = response
      }
    )
  }

}
