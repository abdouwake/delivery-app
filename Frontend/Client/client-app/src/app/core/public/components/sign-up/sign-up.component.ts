import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ScreenTypeService } from '../../services/screen-type.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent implements OnInit {
  constructor(private screenTypeService: ScreenTypeService) {}

  isSmallScreen$: Observable<boolean> = this.screenTypeService.isSmallScreen$;
  isMediumScreen$: Observable<boolean> = this.screenTypeService.isMediumScreen$;
  isLargeScreen$: Observable<boolean> = this.screenTypeService.isLargeScreen$;

  public imageSrc = '';

  public ngOnInit(): void {}
}
