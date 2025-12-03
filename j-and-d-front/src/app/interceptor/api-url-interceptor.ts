import { HttpInterceptorFn } from '@angular/common/http';
import { environment } from '../environments/environment.development';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {
  const apiReq = req.clone({
    url: environment.apiUrl + req.url,
  });

  return next(apiReq);
};
