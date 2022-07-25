const crypto = require('crypto')
const jose = require('node-jose')
const fs = require('fs')

const keystore = jose.JWK.createKeyStore();
const pem = fs.readFileSync("server-pub-key.pem", { encoding: "utf8" });

var props = {
  kid: 'gBdaS-G8RLax2qgObTD94w',
  alg: 'PS256',
  use: 'sig'
};
keystore.generate("RSA", 4096, props).
    then(function(result) {
      // {result} is a jose.JWK.Key
      key = result;

      console.log(key);

      console.log(key.toJSON(true));
    });




