import { Component, Input, OnInit } from '@angular/core';
import { SodaService } from 'src/app/services/soda.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Soda } from 'src/app/models/soda.model';

@Component({
  selector: 'app-soda-details',
  templateUrl: './soda-details.component.html',
  styleUrls: ['./soda-details.component.css']
})
export class SodaDetailsComponent implements OnInit {

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

  updateSoda(): void {
    this.message = '';

    this.sodaService.update(this.currentSoda.id, this.currentSoda)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.message = res.message ? res.message : 'This soda was updated successfully!';
        },
        error: (e) => console.error(e)
      });
  }

  deleteSoda(): void {
    this.sodaService.delete(this.currentSoda.id)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.router.navigate(['/sodas']);
        },
        error: (e) => console.error(e)
      });
  }

}