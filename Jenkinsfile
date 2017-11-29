pipeline {
    agent any

    stages {
        stage("Init") {
            steps {
             echo " - JOB          : ${env.JOB_NAME}"
             echo " - BUILD_NUMBER : ${env.BUILD_NUMBER}"
             echo " - BUILD_ID     : ${env.BUILD_ID}"
            }
        }
        stage('Build') {
            steps {
                withMaven(jdk: '1.8.0'
                , maven: '3.5.0'
                , mavenSettingsConfig: 'cf825232-43e2-40b5-9cd3-923476d7890a'
                , options: [junitPublisher(ignoreAttachments: false)]) {
            
                    sh 'mvn -B -Dmaven.test.skip=true clean package'
                }
            }
        }
    }
}