# @Value Annotation Failing..

... due to Gemfire @Bean setup.

Recreate:

1. Comment out `src/main/java/demo/GemfireConfiguration.java`
1. `./gradlew bootRun`
1. `curl localhost:8080/foo`, expect "@Value is giving us: hello world" (note `src/main/resources/application.yml`)
1. Stop app
1. Uncomment `src/main/java/demo/GemfireConfiguration.java`
1. `./gradlew bootRun`
1. Note error:

>Error creating bean with name 'someController': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private java.lang.String demo.SomeController.someYamlProp; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'some.yaml.prop' in string value "${some.yaml.prop}"
