pipeline {
    agent any

    tools {
        maven '3.5.0'
        jdk '1.8.0'
    }

    stages {
        stage('Init') {
            steps {
             echo " - JOB          : ${env.JOB_NAME}"
             echo " - BUILD_NUMBER : ${env.BUILD_NUMBER}"
             echo " - BUILD_ID     : ${env.BUILD_ID}"
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -Dmaven.test.skip=true clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }
    }
}