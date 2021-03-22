# Back-End Test Framework

Glenn Paul Terre - test framework example

## Assumptions & Notes

* Price values should be numbers [integer, double].
* All invalid requests should return 400.
* TODOS are left intentionally
* Error messaging is intended for dev / sdets.
* Naming throughout the framework is concise for minimal documentation and readable / debuggable error messaging.
* Tests should not contain conditionals
* Test names should follow `$objectUnderTest_$state_$expectedResult`

## Technologies

* [TestNG] - Extensible Integration Test Framework
* [Typesafe Config] - Configuration Library
* [Rest Assured] - Rest Client

## Execution

Tests can be run through the IDE by either the JUnit or TestNG runners. Right click either the test class / test method
or suite file and select the appropriate runner.

Suites are found in the `/src/test/resources/suites` directory.

Tests can also be executed through mvn cli.

```sh
$ mvn clean install -P ${profile} -D env.conf=${configFilename}
$ mvn clean install -P regression -D env.conf=staging
$ mvn clean install -P regression -D env.conf=local
$ mvn clean install
```

Current Profiles:

- regression (default)

Config Files:

application.conf loads global properties then loads the config file specified by env.conf property. -D jvm properties
overrides all properties.

- src/test/resources/application.conf (loaded first)
- src/test/resources/local.conf
- src/test/resources/staging.conf (default)

example running the default profile (regression) and explicitly using staging.conf file and overriding the `sku.api.domain` property.

```sh
$ mvn clean install -D env.conf="staging" -D sku.api.domain="https://www.google.com"
```
The above command will fail due to the sku endpoint not being part of the www.google.com domain. This example was to show the configuration override hierarchy.

## Reports

The HTML reports will be created in one of the following places depending on the method you used for running the tests.
In case you are running using Maven it will be created in the `target/surefire-reports` or `target/failsafe-report`. If
you are running your tests from your IDE, it will be created in `test-output` folder.

An example of an emailable report is provided in the `/src/test/resources/example_report` directory.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[TestNG]: <https://testng.org/doc/>

[Typesafe Config]: <https://github.com/lightbend/config>

[Rest Assured]: <https://github.com/rest-assured/rest-assured/wiki/Usage>
