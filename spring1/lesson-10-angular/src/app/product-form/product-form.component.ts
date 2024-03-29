import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.less']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", "", -1, "", 0);

  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(param => {
      if (param.id !== 'new') {
        this.productService.findById(param.id)
          .subscribe(res => {
              this.product = res;
            },
            err => {
              console.error(err);
            })
      }
    })
  }

  save() {
    this.productService.save(this.product)
      .subscribe(res => {
          console.info(res);
          this.router.navigateByUrl('/product');
        },
        err => {
          console.error(err);
        })
  }
}
