node {

    stage('Info') {
        echo "CHANGE_ID          : $env.CHANGE_ID"
        echo "BRANCH_NAME        : $env.BRANCH_NAME"
        echo "BUILD_DISPLAY_NAME : $env.BUILD_DISPLAY_NAME"
    }

    stage('Checkout') {
     checkout scm
    }
    
    withMaven(jdk: '1.8.0'
                , maven: '3.5.0'
                , mavenSettingsConfig: 'cf825232-43e2-40b5-9cd3-923476d7890a'
                , options: [junitPublisher(ignoreAttachments: false)]) {
        
        stage('Build') {
          sh 'mvn -B -Dmaven.test.skip=true clean package'
        }

        stage('Test') {
          sh 'mvn -B test'
        }
    }
}