plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
	id("org.sonarqube") version "4.4.1.3373"
}

sonar {
	properties {
		property("sonar.projectKey", "HoomGroomA-5_hoomgroom-commerce")
		property("sonar.organization", "hoomies2022")
		property("sonar.host.url", "https://sonarcloud.io")
	}
}

group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.postgresql:postgresql")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("com.h2database:h2")
    testImplementation("org.testcontainers:postgresql")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.cloudinary:cloudinary-http44:1.21.0")
}

tasks.register<Test>("unitTest") {
	description = "Runs unit tests."
	group = "verification"

	filter {
		excludeTestsMatching("*FunctionalTest")
	}
}

tasks.register<Test>("functionalTest") {
	description = "Runs functional tests."
	group = "verification"

	filter {
		includeTestsMatching("*FunctionalTest")
	}
}

tasks.withType<Test>().configureEach {
	useJUnitPlatform()
}

tasks.test {
	filter {
		excludeTestsMatching("*FunctionalTest")
	}

	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		csv.required.set(true)
		html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
	}
}
