def call(buildPlan = [:]) {
  node(buildPlan.jenkinsNode) {
    if (buildPlan.maven.integTest) {
      stage("Verify") {
        sh "mvn verify"
      }
    } else {
      stage("Package") {
        sh "mvn package"
      }
    }
  }
}