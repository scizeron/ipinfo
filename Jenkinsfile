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
    }

    withMaven(
        maven: "3.5.0",
        mavenSettingsFilePath: "${user.home}/.m2/settings.xml",
        mavenLocalRepo: "${user.home}/.m2/repository" ) {
        stages {
            stage('Build') {
                steps {
                    sh 'mvn -B -Dmaven.test.skip=true clean package'
                }
            }
        }
    }
}