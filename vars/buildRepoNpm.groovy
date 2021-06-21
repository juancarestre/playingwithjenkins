def call(buildPlan = [:]) {

    pipeline() {
        agent any
        environment { 
            CC = 'buildPlan.jenkinsNode'
            repotype = 'buildPlan.repoType'
        }
        parameters {
            string(name: 'buildPlan', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
        }

        stages {
            stage ("Git pull"){
                steps{
                    git branch: 'master', url: 'https://github.com/juancarestre/angular_get_started.git'
                    sh 'ls'
                }
            }
            stage("build"){
                steps{
                    sh 'npm install'
                    sh 'npm run build'
                }
            }      
            stage("deploy"){
                steps{
                    echo "aws s3 ls"
                    echo "${params.buildPlan}"
                }
            }
        }
    }

//   node(buildPlan.jenkinsNode) {
//     stage("Install") {
//       sh "npm install"
//     }
//     stage("Build") {
//       sh "npm run build"
//     }
//     if (buildPlan.npm.tslint) {
//       stage("TSlint") {
//         sh "npm run tslint"
//       }
//     }
//     if (buildPlan.npm.eslint) {
//       stage("ESlint") {
//         sh "npm run eslint"
//       }
//     }
//     if (buildPlan.npm.cypress) {
//       stage("Cypress") {
//         sh "npm run e2e:cypress"
//       }
//     }
//   }
}