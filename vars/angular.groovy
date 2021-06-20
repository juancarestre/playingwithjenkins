def call(String justplaying) {
    pipeline {
        agent any
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
                        //sh 'npm run test'
                        sh 'npm run build'
                    }
                }      
                stage("deploy"){
                    steps{
                        echo "aws s3 ls"
                    }
                }
        }
    }
}
