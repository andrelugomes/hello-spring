import com.nimbusds.jose.jwk.JWK
import org.jose4j.jwa.AlgorithmConstraints
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.PublicJsonWebKey
import java.io.File

fun main(args: Array<String>) {
    val jwe =  "eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIn0.Oc4OSUzoHBylpoIDFBmR3P41Quv3WrETc35brOfCTIIj6P6PCNSL2pn1a_mLjgg-eTbK0BTGeyf14eXl1MBZAr7suI5MN1ImHdjWtPNjgc2ggyQkNjkex5ydsKROXwkQUOi-eD7YuqUtAbnDDnaF66izxV1PxNvZSSDOGILCqphRVJ3jIFuWFqZDHcZbabMaHkVF9UsyybYTexdC4st09VnNNYwek2aOJy66pDXKtm7HmOQotJUmHHoQtorQ7jzEMSTDvbhrC67TfClu7ttLtXVB-rYC8wb2ZD0tQyrkfsSq6CJZ5c_rgvAw8kiAqhzLkwoqb64Q12f8RKsRsJCt2Crq5oZEcuGWGU77NNY1IcOLF16L2MxfeLFbLgCOR7sW5Y4duUGYFDOanWMYbo5jkq5rVrdpUVW3ek0h1h2CLFuHUrQjhUAXENdhyJ6fDcpbwRzhVbDBko6hk3h2xZzK5YpWcg7BZaaArtOsZFFnqZv1-oleenY0Y-hcDjjeO2YpJdcDx73qGhUXUaYhqbm4rRJokGQK5Ho6FTUuAAcesK4gxZAAkocX2fAGV8nN5QHMn0-sO0KmPpaf-v-qE6Fv8QY9KLS9xdFfFHJAtijCoHsNeyLgE028SQ7etLGdH2k4suaHlU-5ohDJO5Jipjljsi4woO20IXgFp8xO4E_hoQ0.M1BPebD1BK8618tr.ZRHr2ModdkkHsIdr_Z23jtK2DZgVYukqJmXMNv4iXpqA.iT59WRHFAK3OR_ZLi82RIA"
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

