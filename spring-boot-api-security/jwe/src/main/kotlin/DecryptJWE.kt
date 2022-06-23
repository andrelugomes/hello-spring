import com.nimbusds.jose.jwk.JWK
import org.jose4j.jwa.AlgorithmConstraints
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.PublicJsonWebKey
import java.io.File

fun main(args: Array<String>) {
    val jwe =  "eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIn0.ahYu_1iSmpGpT0CJW_ZgXT9sO0drFHdyuf5vbtLMV38DxMx8Wekh8JeVha3nEp4OlaJPWrJ8bRkeQt2QxYL5wkiyTD3YIccq1r3q4aZXuxcxqYKi60S-tqv8PbR5c2934cxzK9TVDmArmdUs1OU4iCIybIDt2o6Q1PfGeoAMkXCbaitvzN8OvVJFZGXEu3_XLzsQ5QbkTuS1HUDZoCgCnuTJq4pbwQMJmDNySI7O0vZRnq69pnr454MW7v4bUMBXRaIuKRGaOA56ONHjDjluAo0GFC5Dx5eMG2CTGOD_Ldgbk3VwbARSh5x60vj726TQ8yaVksY5XyTgkeHlA7o3t-lvLcA2bLfj_ye07y3AZGHV28Ch84NgYIvdMcEnfkdmj0I6bYaMGGFPRjSx9F6Sq0gAoHLJtdrA5A5eWe0vG3WPA385NMdMIP1z4rdDgNGxN9Ty0CMybi5SSQt-qxcjjs-MlmpsJuqK2XnE-Vc1qpq9V8SpnbbHxp9gLryxewNvY0srILRssii-lxQNS8t_KCdwlUIzLQwPVgiD6Y8qtTYxK1iRj1qj9iYyeyom4ucVeKV3Fp93yXUjmK-h9NpScE9biGgjkPWvfgjCyNKGD28UnDM0BkNu-bDTV2J_oaQ3wAaF_EPYwV-RTOhCgMPEPz4DtTGwleLL9jurLaSpaNM.1mCxf-EbBLn_Y_Yi.t1xGD7iOxPljoFfI3sPVQmVpqMG01B5TwUtW2ZIg3DSO.0cJA7ASV5CA9BJOBLWjvhw"
    val pem = File("/home/andluis/workspace/hello-spring/spring-boot-api-security/jwe/src/main/resources/private_key.pem").readText(Charsets.UTF_8)

    val jwkAsJson = JWK.parseFromPEMEncodedObjects(pem)

    val jsonWebKey = PublicJsonWebKey.Factory.newPublicJwk(jwkAsJson.toJSONString())


    val receiverJwe = JsonWebEncryption()
    receiverJwe.compactSerialization = jwe
    /*receiverJwe.setAlgorithmConstraints(
        AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,
            KeyManagementAlgorithmIdentifiers.RSA1_5,
            KeyManagementAlgorithmIdentifiers.RSA_OAEP,
            KeyManagementAlgorithmIdentifiers.RSA_OAEP_256
        )
    )
    receiverJwe.setContentEncryptionAlgorithmConstraints(
        AlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT,
            ContentEncryptionAlgorithmIdentifiers.AES_256_GCM
        )
    )*/
    receiverJwe.key = jsonWebKey.privateKey // private to decrypt

    println("Content: ${receiverJwe.plaintextString}")
}

