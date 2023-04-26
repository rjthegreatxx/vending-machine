import { Component, OnInit } from '@angular/core';
  import { Soda } from 'src/app/models/soda.model';
  import { SodaService } from 'src/app/services/soda.service';

  @Component({
    selector: 'app-sodas-list',
    templateUrl: './sodas-list.component.html',
    styleUrls: ['./sodas-list.component.css']
  })
  export class SodasListComponent implements OnInit {

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

    removeAllSodas(): void {
      this.sodaService.deleteAll()
        .subscribe({
          next: (res) => {
            console.log(res);
            this.refreshList();
          },
          error: (e) => console.error(e)
        });
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