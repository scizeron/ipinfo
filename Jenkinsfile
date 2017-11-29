node {

    checkout scm
    
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
