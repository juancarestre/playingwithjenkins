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

def merge(Map lhs, Map rhs) {
    return rhs.inject(lhs.clone()) { map, entry ->
        if (map[entry.key] instanceof Map && entry.value instanceof Map) {
            map[entry.key] = merge(map[entry.key], entry.value)
        } else if (map[entry.key] instanceof Collection && entry.value instanceof Collection) {
            map[entry.key] += entry.value
        } else {
            map[entry.key] = entry.value
        }
        return map
    }
}