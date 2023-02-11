import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'selectSignInPicture',
})
export class SelectSignInPicturePipe implements PipeTransform {
  transform(value: string): string {
    let urlBankImg: string = 'assets/signin/';

    switch (value) {
      case 'USER':
        urlBankImg += 'clientSignIn.svg';
        break;
      case 'RESTAURANT':
        urlBankImg += 'restaurant.svg';
        break;
      case 'LIVREUR':
        urlBankImg += 'livreur.svg';
        break;
    }

    return urlBankImg;
  }
}
