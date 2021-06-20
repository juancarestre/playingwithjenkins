def call(givenConfig = [:]) {
  def defaultConfig = [
    /**
      * The Jenkins node, or label, that will be allocated for this build.
      */
    "jenkinsNode": "BUILD",
    /**
      * All config specific to NPM repo type.
      */
    "npm": [
      /**
        * Whether or not to run Cypress tests, if there are any.
        */
      "cypress": false
    ],
    "maven": [
      /**
        * Whether or not to run integration tests, if there are any.
        */
      "integTest": false
    ]
  ]
  // https://e.printstacktrace.blog/how-to-merge-two-maps-in-groovy/
  def effectiveConfig merge(defaultConfig, givenConfig)
  println "Configuration is documented here: https://whereverYouHos/getConfig.groovy"
  println "Default config: " + defaultConfig
  println "Given config: " + givenConfig
  println "Effective config: " + effectiveConfig
  return effectiveConfig
}