def call(givenConfig = [:]) {
  def effectiveConfig = getConfig(givenConfig)
  def buildPlan = getBuildPlan(effectiveConfig)

  if (buildPlan.repoType == 'MAVEN') {
    buildRepoMaven(buildPlan);
  } else if (buildPlan.repoType == 'NPM') {
    buildRepoNpm(buildPlan);
  }
}