import { Component, OnInit } from '@angular/core';
import { ProductService, Product } from 'src/app/services/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService) { }

  // ngOnInit() {
  //   this.productService.getAll()
  //     .subscribe((products: Product[]) => {
  //       this.products = products;
  //     });
      
  // }
  ngOnInit() {
    this.productService.fetchAndInsertDataFromApi()
      .subscribe(
        (apiProducts: Product[]) => {
          this.products = apiProducts;

          // Call the fetchAndInsertData method from ProductService
          this.productService.fetchAndInsertData(this.products)
            .subscribe(
              (response) => {
                console.log('Data fetched and inserted successfully:', response);
              },
              (error) => {
                console.error('Error fetching and inserting data:', error);
              }
            );
        },
        (error) => {
          console.error('Error fetching data from the API:', error);
        }
      );
  }
}