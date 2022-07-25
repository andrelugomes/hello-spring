const crypto = require('crypto')
const jose = require('node-jose')
const fs = require('fs')

const jwks = fs.readFileSync('jwks.json');
var keystore = jose.JWK.createKeyStore();

jose.JWK.asKeyStore(jwks.toString())
    .then(function(jwks) {
        keystore = jwks;
        //console.log(keystore.toJSON(true));

        const sigKeys = keystore.all({ use: 'sig' });

        console.log(sigKeys);


        for (const key of sigKeys) {
            console.log(key.toJSON(true));
            console.log(key.toPEM());
        }
    });


