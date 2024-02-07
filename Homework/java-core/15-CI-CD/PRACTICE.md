# Practice Exercises

Exercise 1:

- Create a pull request that will contain Workflow from https://docs.github.com/en/actions/quickstart guide
- Do not close Pull request and don’t merge changes into a main branch
- As this task is for learning how to work and run Workflows in GitHub Actions but not for regular usage during CI
  processes of repository

Exercise 2:

Lets create an simple Cipher library that can encode/decode messages. To achieve this you need to extract `Caesar`
and `Vigenere` ciphers code into a dedicated Gradle module. Prepare this module for publishing into a remote Maven
repository hosted on a [Repsy.io](http://Repsy.io) using the same credentials as for registration on site. Publish
library to a remote Maven repository during CI. After successful publishing add your library as a dependency to your
Spring project and use encode/decode logic from it.

Criteria:

- Ciphers library should not contain any Spring related dependencies
- [Semantic versioning](https://semver.org/) should be used for dependency version value
- Credentials to Maven Repository should be stored
  in [GitHub repo secrets](https://docs.github.com/en/actions/security-guides/using-secrets-in-github-actions) and
  passed to Gradle as Environment variables
- Publishing logic should be implemented in a `run_lib_publish_job.yml` GitHub Actions workflow
- `!!!` Pipeline should be completed successful at the time of homework check

Materials:

[GitHub Actions - Quickstart](https://docs.github.com/en/actions/quickstart)

[Gradle - Publishing Maven](https://docs.gradle.org/current/userguide/publishing_maven.html)

[Semantic Versioning](https://semver.org/)

[Using Secrets In GitHub Actions](https://docs.github.com/en/actions/security-guides/using-secrets-in-github-actions)

Each student will have own remote Maven repository hosted on [Repsy.io](http://Repsy.io) with the following name
format: `https://repsy.io/mvn/vrudas/<REPSY_MAVEN_REPO_NAME>`

| GeekHub Student    | Repsy Maven Repo Name    | login              | email                          | Access Granted |
|--------------------|--------------------------|--------------------|--------------------------------|----------------|
| Anya Havryliuk     | ahavryliuk-j4w-s13-repo  | annagav            | annagavriluk772@gmail.com      | ✅              |
| Elvira Chala       | echala-j4w-s13-repo      | elvirachala        | elvira.chalaya0018@gmail.com   | ✅              |
| Karina Tkach       | ktkach-j4w-s13-repo      | karinatkach        | ms.karinatk@gmail.com          | ✅              |
| Mykhailo Kaminskyi | mkaminskyi-j4w-s13-repo  | kaminskyi          | kaminskyi.mv@gmail.com         | ✅              |
| Oleksandr Kukotin  | okukotin-j4w-s13-repo    | oleksandr_k        | oleksandr.kukotin@gmail.com    | ✅              |
| Serhii Lotsman     | slotsman-j4w-s13-repo    | slotsman           | lotsman.961@gmail.com          | ✅              |
| Serhii Tserkovnyi  | stserkovnyi-j4w-s13-repo | sergiy413          | dizz413@gmail.com              | ✅              |
| Victoria Babchenko | vbabchenko-j4w-s13-repo  | victoria_babchenko | victoria.babchenko30@gmail.com | ✅              |
| Volodymyr Feofilov | vfeofilov-j4w-s13-repo   | feofilov           | vladimirfiloff@gmail.com       | ✅              |
| Yana Hontarenko    | yhontarenko-j4w-s13-repo | hontiky            | yana.hontarenko@gmail.com      | ✅              |
