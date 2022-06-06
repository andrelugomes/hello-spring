import com.nimbusds.jose.jwk.JWK
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers
import org.jose4j.jwk.PublicJsonWebKey
import java.io.File

fun main(args: Array<String>) {
    //val pem = File("/home/andluis/workspace/hello-spring/spring-boot-api-security/jwe/src/main/resources/private_key.pem").readText(Charsets.UTF_8)
    val pem = File("/home/andluis/workspace/hello-spring/spring-boot-api-security/jwe/src/main/resources/public_key.pem").readText(Charsets.UTF_8) // Receiver Public Key

    val message = "THIS A TOP SECRET INFORMATION!!!!"

    val jwk = JWK.parseFromPEMEncodedObjects(pem)
    println(jwk)

    //val jwkey = JsonWebKey.Factory.newJwk(jwk.toJSONString())
    // PublicJsonWebKey extends JsonWebKey
    var publicJsonWebKey = PublicJsonWebKey.Factory.newPublicJwk(jwk.toJSONString())


    val senderJwe = JsonWebEncryption()
    senderJwe.setPlaintext(message)
    senderJwe.algorithmHeaderValue = KeyManagementAlgorithmIdentifiers.RSA_OAEP_256
    senderJwe.encryptionMethodHeaderParameter = ContentEncryptionAlgorithmIdentifiers.AES_256_GCM
    senderJwe.key = publicJsonWebKey.key //publicKey()
    println("JWE: $senderJwe")

    val compactSerialization = senderJwe.compactSerialization
    println("JWE compact serialization: $compactSerialization")
}

