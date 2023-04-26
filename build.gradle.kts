plugins {
    id("java")
}

group = "at.fhv.lka2"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.json:json:20230227")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}