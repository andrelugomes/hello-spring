const jose = require('node-jose');
const fs = require('fs');


var data = "{\"key\":\"value\"}"

var keystore = jose.JWK.createKeyStore();
const pem = fs.readFileSync("other_private_key.pem", { encoding: "utf8" });
//console.log(pem);

keystore.add(pem, "PEM").
    then(function(result) {
        // {result} is a jose.JWK.Key
        console.log(result);
        //keystore = result;

        jose.JWS.createSign({ format: 'compact' }, result).
                update(data).
                final().
                then(function(result) {
                  console.log(result);
            });

    });


