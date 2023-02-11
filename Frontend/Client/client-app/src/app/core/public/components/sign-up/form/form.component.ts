import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EntryPointAppService } from '../../../services/entry-point-app.service';
import { ScreenTypeService } from '../../../services/screen-type.service';
import { map, Observable } from 'rxjs';
import { RegisterDto } from '../../../models/register.dto';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class FormComponent implements OnInit {
  constructor(
    private signInService: EntryPointAppService,
    private router: Router,
    private screenTypeService: ScreenTypeService
  ) {}

  isSmallScreen$: Observable<boolean> = this.screenTypeService.isSmallScreen$;
  isMediumScreen$: Observable<boolean> = this.screenTypeService.isMediumScreen$;
  isLargeScreen$: Observable<boolean> = this.screenTypeService.isLargeScreen$;

  step: number = 0;
  STEP_MAX: number = 4;

  public changeStepUp(): void {
    this.step = (this.step + 1) % this.STEP_MAX;
  }

  public changeStepDown(): void {
    if (this.step - 1 === -1) {
      alert('Step error');
    } else {
      this.step = this.step - 1;
    }
  }

  role: string = 'USER';
  prenom: string = '';
  nom: string = '';
  email: string = '';
  password: string = '';
  passwordConf: string = '';
  phoneNumb: string = '';

  signin() {
    if (this.password != this.passwordConf) {
      alert('password dont match ! ');
    } else {
      var registerDto: RegisterDto = new RegisterDto(
        this.prenom,
        this.nom,
        this.email,
        this.role,
        this.phoneNumb,
        this.password
      );
      this.signInService.register(registerDto);
    }
  }

  ngOnInit(): void {}

  public redirect(): void {
    this.router.navigate(['/login']);
  }
}
