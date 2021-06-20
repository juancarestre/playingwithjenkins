def call(givenConfig = [:]) {
  def effectiveConfig = getConfig(givenConfig)
  def buildPlan = getBuildPlan(effectiveConfig)

  if (effectiveConfig.repoType == 'MAVEN') {
    buildRepoMaven(buildPlan);
  } else if (effectiveConfig.repoType == 'NPM') {
    buildRepoNpm(buildPlan);
  }
}