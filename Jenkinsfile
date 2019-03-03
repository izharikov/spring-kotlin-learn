pipeline {
    agent {
        docker {
            image 'gradle:jdk10'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle jar'
            }
        }
    }
}