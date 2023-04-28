import { Component, OnInit } from '@angular/core';
import { Soda } from 'src/app/models/soda.model';
import { SodaService } from 'src/app/services/soda.service';

@Component({
  selector: 'app-vendingmachine-list',
  templateUrl: './vendingmachine-list.component.html',
  styleUrls: ['./vendingmachine-list.component.css']
})
export class VendingmachineListComponent implements OnInit {
  sodas?: Soda[];
  currentSoda: Soda = {};
  currentIndex = -1;
  name = '';

  constructor(private sodaService: SodaService) { }

  ngOnInit(): void {
    this.retrieveSodas();
  }

  retrieveSodas(): void {
    this.sodaService.getAll()
      .subscribe({
        next: (data) => {
          this.sodas = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

  refreshList(): void {
    this.retrieveSodas();
    this.currentSoda = {};
    this.currentIndex = -1;
  }

  setActiveSoda(soda: Soda, index: number): void {
    this.currentSoda = soda;
    this.currentIndex = index;
  }

  searchName(): void {
    this.currentSoda = {};
    this.currentIndex = -1;

    this.sodaService.findByName(this.name)
      .subscribe({
        next: (data) => {
          this.sodas = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }

}