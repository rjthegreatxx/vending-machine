import { Component, Input, OnInit } from '@angular/core';
import { SodaService } from 'src/app/services/soda.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Soda } from 'src/app/models/soda.model';

@Component({
  selector: 'app-soda-purchase',
  templateUrl: './soda-purchase.component.html',
  styleUrls: ['./soda-purchase.component.css']
})
export class SodaPurchaseComponent {
  @Input() viewMode = false;

  @Input() currentSoda: Soda = {
    name: '',
    price: 0.0,
    quantity: 0
  };

  message = '';

  constructor(
    private sodaService: SodaService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    if (!this.viewMode) {
      this.message = '';
      this.getSoda(this.route.snapshot.params["id"]);
    }
  }

  getSoda(id: string): void {
    this.sodaService.get(id)
      .subscribe({
        next: (data) => {
          this.currentSoda = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  purchaseSoda(): void {
    this.message = '';

    this.sodaService.purchase(this.currentSoda.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = 'This soda was purchased successfully!';
        },
        error: (e) => console.error(e)
      });
  }
}
