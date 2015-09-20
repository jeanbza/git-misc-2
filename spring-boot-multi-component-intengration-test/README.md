# Integration Test Runner with a Multi-Component Setup

1. Take a look at `server/src/test/java/ComponentsIntegrationTest`
1. Note that it starts the app using `@IntegrationTest("server.port:0")` and `@SpringApplicationConfiguration(classes = DemoApplication.class)`
1. Note that the test requires `FooController.java` and `BarController.java` - both in other components - to be loaded
1. `./gradlew build` (clean, compile, test)

Success!