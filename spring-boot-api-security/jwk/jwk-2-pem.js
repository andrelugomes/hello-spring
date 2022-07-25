const crypto = require('crypto')
const jose = require('node-jose')
const fs = require('fs')

const keystore = jose.JWK.createKeyStore();
keystore.add(fs.readFileSync('jwk.json'), 'json')
  .then(function(k) {
    console.log(k.toJSON(true)); //export the public and private portions of a Key

    console.log("PUBLIC KEY");
    const pem = k.toPEM();
    console.log(pem);

    console.log("CERTIFICATE");
    const jwk = k.toObject();
    const cert = new crypto.X509Certificate(Buffer.from(jwk.x5c[0], 'base64'))
    console.log(cert.toString());
  });


