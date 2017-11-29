node {
    def scmVars = null
    def commitHash = null
    def branch = null;
  
    stage('Checkout') {
     scmVars = checkout scm
     commitHash = scmVars.GIT_COMMIT
     branch = scmVars.GIT_BRANCH
    }
    
    stage('Info') {
        echo "commitHash         : ${commitHash}"
        echo "BRANCH_NAME        : ${branch}"
        echo "BUILD_DISPLAY_NAME : $env.BUILD_DISPLAY_NAME"
    }

}