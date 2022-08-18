const jose = require('node-jose');
const fs = require('fs');

const jws = "eyJhbGciOiJSUzI1NiIsImtpZCI6IkJ3R0VXMDR0TlozNmxoMTZiOTZaTGU1N3Z0MUtrc3ltYXNibjJmN3FzbTQifQ.eyJrZXkiOiJ2YWx1ZSJ9.LvPYz99qjOQ6EvnNOQ5OwF9ZPk9dxnT2pTRrbZCQhzUbWDPlmUNWDlm8pqs-ieAQfFC0eye90NXDRlheYFoHfbPSXoeTkSUEEgyrt8BHS1_2g8M8OWmCQmjf_6BZrTRD0r6NXPqmt917l_sUyTIqwtO4aH2OT42hPHe8G4YaJYv0EuGbL-lll3GLOEHdLZa7JretfqY7OP4SSabZ2Zd_3bOspg9cjm5xhPSMUW7r1cXLoapa0OObiB6YwYnc6qc5w3wIjZtCmxhvU4bMV4U_0ZKpxX5Mt9L5nq3DKXwMG-b-z0JcYT8SBJS9ycgC63cnNuIkk8D4SWlO0TH2zCqasnyFpf2iKPWd30N8g1anOfCa93xrsdOqiYpJCuKCtqwluqsyn1m5PSunL4Vrga39ShrUW_Ho7DvEodNCUfQ95-WNDHnOubkEAbjn7cBTf-YfFoB1SL3VSET9AdKDAXEKnRatT3_ed92S3uNea2j9s-cbIB8ivv1NB3VENePkkxj98dOaBIxl-HnSluwXixHYW2PmXUsG2OivlAlTZwQ6UGrShlYrBcOMTk8-WYQldjieC-hRxI--slodAKfYPkWzl8G5Wn09NYIqBzkm42PvfwgZ8FCjer-f-KzBSnStH5APTiuAcK4i3qKSPqNMZtrm9y5EKUfz3EkpwIq0S7iUGvA";
const keystore = jose.JWK.createKeyStore();
const pem = fs.readFileSync("other_public_key.pem", { encoding: "utf8" });

keystore.add(pem, "PEM").
    then(function(key) {
        // {Key} is a jose.JWK.Key
console.log(key);
        jose.JWS.createVerify(key).
            verify(jws).
            then(function(result) {
              console.log(result);
            }).
            catch(function(error) {
                console.log(error);
            });
    });
