def call(effectiveConfig = [:]) {
  def derivedBuildPlan = [
    "repoType": "NOT DETECTED",
    "npm": [],
    "maven": []
  ]

  node {
    deleteDir()
    checkout([$class: 'GitSCM',
      branches: [[name: '*/branchName']],
      extensions: [
          [$class: 'SparseCheckoutPaths',
            sparseCheckoutPaths:
            [[$class:'SparseCheckoutPath', path:'package.json,pom.xml']]
          ]
      ],
      userRemoteConfigs: [[credentialsId: 'someID',
      url: 'git@link.git']]
    ])

    if (fileExists('package.json')) {
      def packageJSON = readJSON file: 'package.json'
      derivedBuildPlan.repoType = "NPM"
      derivedBuildPlan.npm.cypress = effectiveConfig.npm.cypress && packageJSON.devDependencies.cypress
      derivedBuildPlan.npm.eslint = packageJSON.devDependencies.eslint
      derivedBuildPlan.npm.tslint = packageJSON.devDependencies.tslint
    } else if (fileExists('pom.xml')) {
      derivedBuildPlan.repoType = "MAVEN"
      derivedBuildPlan.maven.integTest = effectiveConfig.maven.integTest && fileExists('src/integtest')
    } else {
      throw RuntimeException('Unable to detect repoType')
    }

    println "Build plan: " + derivedBuildPlan
    deleteDir()
  }
  return derivedBuildPlan
}