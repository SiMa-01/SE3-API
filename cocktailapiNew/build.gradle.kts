import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	//kotlin("multiplatform")
	//kotlin("native.cocoapods")
	//id("com.android.library")
	//id("io.realm.kotlin") version "1.7.1" apply false

	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
	mavenCentral()
}

dependencies {
	//implementation("io.realm.kotlin:library-sync:1.7.1")
	//implementation ("org.mongodb:mongodb-driver-sync:4.9.1")
	//implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")
	//implementation ("org.mongodb:mongodb-driver-sync:<version>")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	//implementation("org.restheart:restheart-mongoclient-provider:7.3.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
//properties spring.main.allow-bean-definition-overriding=true


