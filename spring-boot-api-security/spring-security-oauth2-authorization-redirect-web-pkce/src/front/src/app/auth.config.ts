import {AuthConfig} from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  issuer: 'http://localhost:8080/realms/api-authorization',
  //redirectUri: window.location.origin,
  redirectUri: 'http://localhost:4200',
  clientId: 'angular-authorization-redirect-web-pkce',
  responseType: 'code',
  strictDiscoveryDocumentValidation: false,
  scope: 'openid profile email offline_access',
}
