const crypto = require('crypto')
const jose = require('node-jose')
const fs = require('fs')

const keystore = jose.JWK.createKeyStore();
const pem = fs.readFileSync("server-pub-key.pem", { encoding: "utf8" });

jose.JWK.asKey(pem, "PEM").
    then(function(key) {
        // {key} is a jose.JWK.Key
        console.log(key);
        console.log(key.toJSON(true));


  });
