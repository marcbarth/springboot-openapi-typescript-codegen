import {Component, inject, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {Person, PersonControllerService} from "../generated";
import {NgForOf} from "@angular/common";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgForOf, ReactiveFormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  personService = inject(PersonControllerService)
  persons: Person[] = []
  form = new FormGroup({
    firstName: new FormControl(''),
    familyName: new FormControl('')
  });

  ngOnInit(): void {
    this.fetchPersons()
  }

  fetchPersons() {
    this.personService.getAllPerson('body').subscribe(res => this.persons = res)
  }

  submit() {
    this.personService.createPerson(this.form.value as Person, 'body').subscribe(res =>
      this.persons.push(res)
    )
  }

  deletePerson(person: Person) {
    this.personService.deletePerson(person.id!!, 'response').subscribe((res) => {
      if (res.status === 200) {
       this.persons = this.persons.filter( item => person.id !== item.id)
      }
    })
  }

}

