pipeline {
    agent any
    environment {
        DOCKERHUB_USER = 'inddocker786'
        IMAGE_NAME = "${DOCKERHUB_USER}/softoolshop"
    }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Maven Build') {
            steps { sh 'mvn -B clean package -DskipTests' }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${IMAGE_NAME}:${env.BUILD_NUMBER} -t ${IMAGE_NAME}:latest ."
                }
            }
        }
        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh 'echo $PASS | docker login -u $USER --password-stdin'
                    sh "docker push ${IMAGE_NAME}:${env.BUILD_NUMBER}"
                    sh "docker push ${IMAGE_NAME}:latest"
                }
            }
        }
    }
}
