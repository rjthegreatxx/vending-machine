import { Component } from '@angular/core';
import { Soda } from 'src/app/models/soda.model';
import { SodaService } from 'src/app/services/soda.service';

@Component({
  selector: 'app-add-soda',
  templateUrl: './add-soda.component.html',
  styleUrls: ['./add-soda.component.css']
})
export class AddSodaComponent {

  soda: Soda = {
    name: '',
    price: 0.0,
    quantity: 0
  };
  submitted = false;

  constructor(private sodaService: SodaService) { }

  saveSoda(): void {
    const data = {
      name: this.soda.name,
      price: this.soda.price,
      quantity: this.soda.quantity
    };

    this.sodaService.create(data)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  newSoda(): void {
    this.submitted = false;
    this.soda = {
      name: '',
      quantity: 0
    };
  }

}