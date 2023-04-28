import { Component, Input, OnInit } from '@angular/core';
import { VendingmachineService } from 'src/app/services/vendingmachine.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Soda } from 'src/app/models/soda.model';
import { Purchasesodadto } from 'src/app/models/purchasesodadto.model';
import { Returnsodadto } from 'src/app/models/returnsodadto.model';

@Component({
  selector: 'app-vendingmachine-details',
  templateUrl: './vendingmachine-details.component.html',
  styleUrls: ['./vendingmachine-details.component.css']
})
export class VendingmachineDetailsComponent implements OnInit {
  submitted = false;
  depositAmount = 0.00.toFixed(2);
  message: string = '';

  @Input() viewMode = false;

  @Input() currentSoda: Soda = {
    name: '',
    price: 0.0,
    quantity: 0
  };

  purchasesodadto: Purchasesodadto = {
    id: 0,
    quantity: 0,
    depositAmount: ''
  };

  returnsodadto: Returnsodadto = {
    name: '',
    quantity: 0,
    msg: '',
    changeAmount: ''
  };

  constructor(
    private vendingmachineService: VendingmachineService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
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

  onPurchaseSoda() {
    const depositAmountNumber = parseFloat(this.depositAmount);
    if (this.currentSoda && this.currentSoda.price && depositAmountNumber < this.currentSoda.price) {
      this.message = 'Please add more quarters';
    } else {
      this.purchasesodadto.id = this.currentSoda.id;
      this.purchasesodadto.quantity = 1;
      this.purchasesodadto.depositAmount = this.depositAmount;

      this.vendingmachineService.purchaseSoda(this.purchasesodadto)
        .subscribe({
          next: (res) => {
            this.returnsodadto = res;
            console.log(res);
            this.submitted = true;
          },
          error: (e) => console.error(e)
        });
    }
  }
}