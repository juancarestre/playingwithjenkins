def call(buildPlan = [:]) {
  node(buildPlan.jenkinsNode) {
    stage("Install") {
      sh "npm install"
    }
    stage("Build") {
      sh "npm run build"
    }
    if (buildPlan.npm.tslint) {
      stage("TSlint") {
        sh "npm run tslint"
      }
    }
    if (buildPlan.npm.eslint) {
      stage("ESlint") {
        sh "npm run eslint"
      }
    }
    if (buildPlan.npm.cypress) {
      stage("Cypress") {
        sh "npm run e2e:cypress"
      }
    }
  }
}