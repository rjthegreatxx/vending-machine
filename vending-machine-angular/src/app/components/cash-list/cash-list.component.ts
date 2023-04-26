import { Component, OnInit } from '@angular/core';
import { CashService } from 'src/app/services/cash.service';
import { Cash } from 'src/app/models/cash.model';
import { Totalcashdto } from 'src/app/models/totalcashdto.model';

@Component({
  selector: 'app-cash-list',
  templateUrl: './cash-list.component.html',
  styleUrls: ['./cash-list.component.css']
})
export class CashListComponent implements OnInit {
  totalCashDto: any;
  returnCashDto: any;
  submitted = false;

  constructor(private cashService: CashService) { }

  ngOnInit(): void {
    this.retrieveTotalCashDto();
  }


  retrieveTotalCashDto(): void {
    this.cashService.getTotalCashDto()
      .subscribe({
        next: (data) => {
          this.totalCashDto = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  dispenseQuarters(): void {
    const data = {
      type: "Quarters",
      quantity: 1
    };

    this.cashService.dispenseQuarters(data)
      .subscribe({
        next: (res) => {
          this.returnCashDto = res;
          console.log(res);
          this.submitted = true;
        },
        error: (e) => console.error(e)
      });
  }

  reloadPage() {
    window.location.reload();
  }
}
