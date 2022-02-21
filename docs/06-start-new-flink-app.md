# Flink App

- Use any of your Java IDE to start a new maven project. - intellij in our case
- install maven **sudo apt install maven**

## Examples

### hello-world-java-project

word count example

Name: hello-world
GroupId: learn-flink
ArtifactId: hello-world
Version: 1.0-SNAPSHOT

```sh
mvn clean
mvn install
# once install is successful, go to pom.xml >> right click >> maven >> reload project
```

### joins

demonstration of different kind of joins

- inner join
- full outer join
- left outer join
- right outer join

To execute one change the entrypoint of the JAR in manifest. Provide Join hints to Flink

Name: joins
GroupId: learn-flink
ArtifactId: joins
Version: 1.0-SNAPSHOT

## Intellij Idea stuff

**Create a jar file in intellij idea**:

File >> Project Structure >> Artifact >> + >> JAR >> From module and dependencies

Mention the directory where Manifest.mf is defined.
