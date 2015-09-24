# Testing Gemfire with JUnit

## Setup

1. Install JDK8
1. [Install PhantomJS](http://phantomjs.org/download.html) (for headless browser testing)
    1. Download the [phantomjs binary](https://github.com/eugene1g/phantomjs/releases/tag/2.0.0-bin)
    1. `cp -r ~/Downloads/phantomjs ~/dev/` (or wherever you prefer)
    1. `echo "export PATH:$PATH:~/dev/phantomjs" >> ~/.bash_profile"`
    1. Ensure `which phantomjs` works
1. Install gfsh `brew tap pivotal/tap && brew install gemfire`

## Running tests (kind of the point of this thing!)

1. `bin/gemfire.sh start`
1. `./gradlew test`
1. `bin/gemfire.sh stop`

## Running the app

1. `bin/gemfire.sh start`
1. `gfsh -e "connect" -e "put --key=abc --value=4 --region=FooRegion"`
1. `gfsh -e "connect" -e "put --key=xyz --value=7 --region=FooRegion"`
1. `./gradlew bootRun`
1. `curl localhost:8080/foo`
1. `bin/gemfire.sh stop`

# Interesting things

- When we run Acceptance tests, we MUST cache our Region connection: There can only be a single instance of each region,
  and when acceptance tests run we need the same connection in two places - once in our acceptance test, and once in
  our code itself (both of which are running in the same context). This is why you will find a `ConcurrentHashMap` caching
  each new `Region` connection
- JUnit UNIT tests lack a couple of niceties; since the spring context is not loaded when they run, they do not have
  access to `@Value` props (like, gemfire config in `application.yml` ) or `@Bean`s (like, `@Bean Region myRegion`), so
  we end up having to do some manual work to set these up
- Something in [the spring-data annotation way of setting up gemfire](https://github.com/jadekler/git-misc-2/blob/master/java-value-annotation-failure/server/src/main/java/demo/GemfireConfiguration.java)
  causes `@Value` to stop working