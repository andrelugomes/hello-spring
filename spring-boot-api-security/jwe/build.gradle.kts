plugins {
    kotlin("jvm") version "1.6.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.bitbucket.b_c:jose4j:0.7.12")

    implementation("com.nimbusds:nimbus-jose-jwt:9.23")
    // https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on
    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
    implementation("org.bouncycastle:bcutil-jdk15on:1.70")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")





}