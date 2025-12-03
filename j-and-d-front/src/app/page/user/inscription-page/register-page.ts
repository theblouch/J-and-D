import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SubscribeRequestDto } from '../../../dto/subscribe-request-dto';
import { UserService } from '../../../service/user-service';
import { passwordMatchValidator } from '../../../validator/password-match-validator';

@Component({
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './register-page.html',
  styleUrl: './register-page.css',
})
export class InscriptionPage implements OnInit {
  protected subscriptionError: boolean = false;

  protected userForm!: FormGroup;
  protected loginCtrl!: FormControl;
  protected passwordCtrl!: FormControl;
  protected passwordConfirmCtrl!: FormControl;
  protected roleCtrl!: FormControl;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginCtrl = this.formBuilder.control('', Validators.required);
    this.passwordCtrl = this.formBuilder.control('', [
      Validators.required,
      Validators.minLength(2),
    ]);
    this.passwordConfirmCtrl = this.formBuilder.control('', [
      Validators.required,
      Validators.minLength(6),
    ]);

    this.roleCtrl = this.formBuilder.control('player', Validators.required);

    this.userForm = this.formBuilder.group(
      {
        login: this.loginCtrl,
        password: this.passwordCtrl,
        passwordConfirm: this.passwordConfirmCtrl,
        role: this.roleCtrl,
      },
      {
        validators: passwordMatchValidator('password', 'passwordConfirm'),
      }
    );
  }

  public async connecter() {
    try {
      await this.userService.subscribe(
        new SubscribeRequestDto(this.loginCtrl.value, this.passwordCtrl.value, this.roleCtrl.value)
      );

      this.router.navigate(['/login']);
    } catch {
      // Si la connexion n'a pas pu se faire, affichage de l'erreur sur le template
      this.subscriptionError = true;
    }
  }
}
