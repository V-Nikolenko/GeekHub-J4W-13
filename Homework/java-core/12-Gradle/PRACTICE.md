# Practice Exercises

## Exercise 1

- Fix the project build error that happened because of mistake in Gradle configuration properties
    - project build/refresh operation executes successfully

## Exercise 2

- Migrate all `build.gradle` and `settings.gradle` files to Kotlin DSL
    - all files should have extension `*.kts`
    - all files contain valid Kotlin Gradle DSL code
    - project build/refresh operation executes successfully

## Exercise 3

- Configure usage of the latest Gradle version by using the Gradle wrapper task
    - Gradle version is configured in the root `build.gradle.kts` file
    - Gradle version is updated in the `gradle/wrapper/gradle-wrapper.properties` file by `wrapper` task, but not 
      **manually**
    - project build/refresh operation executes successfully

## Exercise 4

- Implement a simple archive(compression) task:
    - Create a task of type `Tar` that will archive all `*.text` files from the `src/main/resources` directory into a
      tar archive under `build/archive` directory
    - During compression rename extension of each file from `*.text` to `*.txt`
    - File should be compressed using `Gzip` algorithm !!!
    - The compression task should render an info message before packaging files

## Exercise 5

- Create task that will trigger(run) the archive task from the previous exercise
- Create a task that will unarchive(decompress) the tar archive into the `src/test/resources` directory
- The unarchive task should render an info message after unpacking files
- Create a `cleanup` task that will delete all files from the `build/archive` and `src/test/resources` directories

## Exercise 6

- Move all tasks from exercises **4** an **5** into a separate
  Gradle build script `tar-compression.gradle.kts` and apply them
  to `:tar-tasks` project to make this functionality
  available as a script plugin

## Exercise 7

- To complete this task you need to research a little bit
  about [code generation concepts](https://en.wikipedia.org/wiki/Code_generation)
- Under `src/main/resources` directory we have an `openapi.yaml` file that contains a simple OpenAPI specification for
  Caesar and Vigenere ciphers you `DONT NEED TO MODIFY THIS FILE` but juts use it on next step
- Configure a code generation task that will generate a Java source code for the `CaesarCipher` and `VigenereCipher`
  classed based on the `openapi.yaml` file
    - For generation
      use [OpenAPI generator Gradle plugin](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-gradle-plugin/README.adoc)
    - Configure the plugin to generate Java source code DTO models of ciphers data
    - Implement tests that use generated DTO models to test the ciphers functionality
    - Implement encode/decode functionality for both ciphers 
