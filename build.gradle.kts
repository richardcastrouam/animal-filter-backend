plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"

}

group = "com.animalfilter"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// --- CORE SPRING BOOT ---
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// --- FIREBASE (Auth, Firestore, Storage) ---
	implementation("com.google.firebase:firebase-admin:9.4.3")

	// --- SNAPCHAT-STYLE FILTERS (PROCESAMIENTO GRÁFICO Y VIDEO) ---
	// Optimizamos para descargar solo lo necesario para Windows x64
	implementation("org.bytedeco:javacv:1.5.10")
	implementation("org.bytedeco:opencv:4.9.0-1.5.10:windows-x86_64")
	implementation("org.bytedeco:ffmpeg:6.1.1-1.5.10:windows-x86_64")
	implementation("org.bytedeco:openblas:0.3.26-1.5.10:windows-x86_64")

	// Para redimensionar fotos de perfil y generar miniaturas rápido
	implementation("net.coobird:thumbnailator:0.4.20")

	// --- TESTING ---
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}