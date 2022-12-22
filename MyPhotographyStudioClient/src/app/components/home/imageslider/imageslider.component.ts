import { Component } from '@angular/core';

@Component({
  selector: 'app-imageslider',
  templateUrl: './imageslider.component.html',
  styleUrls: ['./imageslider.component.css'],
})
export class ImagesliderComponent {
  slides = [
    { img: '../../../../assets/A.png' },
    { img: '../../../../assets/angular.png' },
    { img: 'https://source.unsplash.com/800x600/?sea' },
    { img: 'https://source.unsplash.com/800x600/?forest' },
    { img: 'https://source.unsplash.com/800x600/?stars' },
    { img: 'https://source.unsplash.com/800x600/?horizon' },
  ];

  slideConfig = {
    autoplay: true,
    vertical: true,
    infinite: true,
    slidesPerRow: 4,
    slidesToShow: 4,
    autoplaySpeed: 2000,
  };
}
