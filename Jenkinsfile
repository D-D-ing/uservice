pipeline {
  agent {
    docker {
      image 'gradle:5.2.1-jdk8-alpine'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'gradle clean install'
        sh 'gradle build'
      }
    }
    stage('Test') {
      steps {
        sh 'gradle test'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Should deploy now.'
      }
    }
  }
}