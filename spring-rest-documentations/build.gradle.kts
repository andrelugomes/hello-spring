import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.asciidoctor.convert") version "1.5.8"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
}

group = "com.example.rest-documentations"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

val snippetsDir by extra { file("build/generated-snippets") }
val asciidoctorOutputDir by extra { file("build/asciidoc/html5") }

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Swagger
	//implementation("io.springfox:springfox-swagger2:2.9.2")
	//implementation("io.springfox:springfox-bean-validators:2.9.2")
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("io.springfox:springfox-boot-starter:3.0.0")

	//Springdocs - Overrides Swagger
	implementation("org.springdoc:springdoc-openapi-ui:1.5.6")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.5.6")


	//rest docs
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
	asciidoctor("org.springframework.restdocs:spring-restdocs-asciidoctor")

	testImplementation("com.atlassian.oai:swagger-request-validator-core:2.15.1")
	testImplementation("com.atlassian.oai:swagger-request-validator-mockmvc:2.15.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.test {
	outputs.dir(snippetsDir)
	useJUnitPlatform()
}

tasks.asciidoctor {
	inputs.dir(snippetsDir)
	dependsOn(tasks.test)
}

tasks.bootJar {
	dependsOn(tasks.asciidoctor)
	from (asciidoctorOutputDir) {
		into("static/docs")
	}
}
