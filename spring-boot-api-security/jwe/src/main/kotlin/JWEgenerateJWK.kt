import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.PublicJsonWebKey
import org.jose4j.jwk.RsaJwkGenerator


fun main(args: Array<String>) {
    // Generate a new RSA key pair wrapped in a JWK
    val rsaJwk: PublicJsonWebKey = RsaJwkGenerator.generateJwk(2048)
    println(rsaJwk)

    // A JSON string with only the public key info
    val publicKeyJwkString = rsaJwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY)
    println(publicKeyJwkString)

    // A JSON string with both the public and private key info
    val keyPairJwkString = rsaJwk.toJson(JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE)
    println(keyPairJwkString)

    // parse and convert into PublicJsonWebKey/JsonWebKey objects
    val parsedPublicKeyJwk = PublicJsonWebKey.Factory.newPublicJwk(publicKeyJwkString)
    val parsedKeyPairJwk = PublicJsonWebKey.Factory.newPublicJwk(keyPairJwkString)

    // the private key can be used to sign (JWS) or decrypt (JWE)
    val privateKey = parsedKeyPairJwk.privateKey
    println(privateKey)

    // the public key can be used to verify (JWS) or encrypt (JWE)
    val publicKey = parsedPublicKeyJwk.publicKey
    println(publicKey)


    val message = "THIS A TOP SECRET INFORMATION!!!!"

    val senderJwe = JsonWebEncryption()
    senderJwe.setPlaintext(message)
    senderJwe.algorithmHeaderValue = KeyManagementAlgorithmIdentifiers.RSA1_5
    senderJwe.encryptionMethodHeaderParameter = ContentEncryptionAlgorithmIdentifiers.AES_256_CBC_HMAC_SHA_512
    senderJwe.key = parsedPublicKeyJwk.key //publicKey()

    val compactSerialization = senderJwe.compactSerialization

    println("JWE compact serialization: $compactSerialization")

}

