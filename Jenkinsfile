node {

    stage('Info') {
        echo "CHANGE_ID          : $env.CHANGE_ID"
        echo "BRANCH_NAME        : $env.BRANCH_NAME"
        echo "BUILD_DISPLAY_NAME : $env.BUILD_DISPLAY_NAME"
    }

    stage('Checkout') {
     checkout scm
    }
    

}