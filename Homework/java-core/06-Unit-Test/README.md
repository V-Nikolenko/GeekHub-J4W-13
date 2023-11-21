Java Application for Fetching Random Facts about Cats

Create a Java application that fetches random facts about cats from the Cat Fact API (https://catfact.ninja/fact) and writes them to a file at regular intervals. The application should be configurable with the time interval and the file path, either through system environment variables or command-line arguments.

Requirements:
- Use the Apache HTTP client to make requests to the Cat Fact API and retrieve random facts about cats.
- The application should retrieve the time interval for fetching facts from the system environment or command-line arguments.
- Facts should be written to a file every specified time interval. The file path should also be configurable from the system environment or command-line arguments.
- Implement a retry mechanism to fetch new facts if the API response is unsuccessful or duplicated (already exists in file). Allow up to 5 retries before writing "I don't know any new facts" to the file.
- Achieve 100% code coverage.
- Optionally, provide support for JaCoCo to generate code coverage reports for the application.



