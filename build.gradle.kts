import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
	id("org.springframework.boot") version "2.1.9.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
}

group = "com.github.ojacquemart.api.restaurant"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	val coroutinesVersion = "1.3.2"
	val jsoupVersion = "1.8.3"
	val assertJVersion = "3.4.1"
	val mockitoVersion = "1.10.19"
	val powerMockVersion = "1.6.5"
	val springMockkVersion = "1.1.3"

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

	implementation("org.jsoup:jsoup:$jsoupVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.assertj:assertj-core:$assertJVersion")
	testImplementation("org.mockito:mockito-core:$mockitoVersion")
	testImplementation("org.powermock:powermock-api-mockito:$powerMockVersion")
	testImplementation("org.powermock:powermock-module-junit4:$powerMockVersion")
	testImplementation("com.ninja-squad:springmockk:$springMockkVersion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
