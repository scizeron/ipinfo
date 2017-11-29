node {
    def scmVars = null
    def commitHash = null
  
    stage('Checkout') {
     scmVars = checkout scm
     commitHash = scmVars.GIT_COMMIT
    }
    
    stage('Info') {
        echo "commitHash         : ${commitHash}"
        echo "BRANCH_NAME        : $env.BRANCH_NAME"
        echo "BUILD_DISPLAY_NAME : $env.BUILD_DISPLAY_NAME"
    }

}