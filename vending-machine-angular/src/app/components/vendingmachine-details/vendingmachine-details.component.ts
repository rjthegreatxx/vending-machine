import { Component, Input, OnInit } from '@angular/core';
import { VendingmachineService } from 'src/app/services/vendingmachine.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Soda } from 'src/app/models/soda.model';

@Component({
  selector: 'app-vendingmachine-details',
  templateUrl: './vendingmachine-details.component.html',
  styleUrls: ['./vendingmachine-details.component.css']
})
export class VendingmachineDetailsComponent implements OnInit {

  @Input() viewMode = false;

  @Input() currentSoda: Soda = {
    name: '',
    price: 0.0,
    quantity: 0
  };

  message = '';

  constructor(
    private vendingmachineService: VendingmachineService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getSoda(this.route.snapshot.params["id"]);
    }
  }

  getSoda(id: string): void {
    this.vendingmachineService.get(id)
      .subscribe({
        next: (data) => {
          this.currentSoda = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

}