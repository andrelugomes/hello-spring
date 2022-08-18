import com.nimbusds.jose.jwk.JWK
import org.jose4j.jwa.AlgorithmConstraints
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers
import org.jose4j.jwe.JsonWebEncryption
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers
import org.jose4j.jwk.JsonWebKey
import org.jose4j.jwk.PublicJsonWebKey
import java.io.File

fun main(args: Array<String>) {
    val jwe =  "eyJhbGciOiJSU0EtT0FFUC0yNTYiLCJlbmMiOiJBMjU2R0NNIn0.J68XsI5ol5b5O6Y4qh7UMp4zV-ZPtuwX9RQIdisKmChwaX9pnHeJiQTm4RsekwdvCOZUsB_S9H8jXZ8gTWS8PC7twzLeMVzW-LqyGeUoGw8h-tGZBaG5cfIQjLON6JYGuQe1x5TbBQTsLR1jaHn0Go1fGvMftT6PAQf5P-rridvKrnXzRvS2vcvKTg10tXBO1gnUJ53FB9Fl208IUwynH53EfZalDOTNkAdZGtBhVIVqAWG0wvJuZWtaan6jJ0umvgtuNQvfBxtJ1Sr1MSGo-RXVw81z-07vxVC0DywskuRCGbecePZBuPKaE2tP4AiOq2vquFEQVvBiM9lZeVCk5MZTAfmabLWb8nd2XEDHKlhXw6gHxdpiSItKvEO9xZiXg9MpNSPTEKlk6nrMMOwx_5yqkDNXsj3KBuGHOSA3gpay3G5wbmPNRYTBmXbhyVTNbl_bnDmKj_-Qh95fcsKnK0eNUAVKsEfo92J0vP5g8aTphZBWC-YvnTm2gPy6zI6ZGC0JIHWLaOwuwMWvLLaN21VYUUtehHP67qTR_HgxCd72n9pEpusL5cyYDnU3XOXcU1GD9rGtgLhbBUVcqBGweRe4Y2CYAN9I67UXMCS_oUuRGMFUApaNBGTMrrtg9Q-wjZU_abvkYMSRzo6-6-iSS8nbts4X6E7NvT7woOqzdIs.iMyG8m1XCDFGXoZy.WiRt2yY3v5BikPj5d5tyk9any8eXY-uTmzKir7fZYZE1.Ik7p2ekLLMo9TsoBlxcvQA"
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

