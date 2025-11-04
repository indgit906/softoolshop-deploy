pipeline {
    agent any
    environment {
        DOCKER_HUB_USER = credentials('dockerhub-username')
        DOCKER_HUB_PASS = credentials('dockerhub-password')
        IMAGE_NAME = "${DOCKER_HUB_USER}/softoolshop"
    }

    stages {
        stage('Checkout SCM') {
            steps { checkout scm }
        }

        stage('Build JAR') {
            steps {
                script {
                    docker.image('maven:3.9.6-eclipse-temurin-17').inside('-v $HOME/.m2:/root/.m2') {
                        sh 'mvn clean package -DskipTests'
                        sh 'mv target/*.jar target/backend.jar'
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps { sh "docker build -t ${IMAGE_NAME}:latest ." }
        }

        stage('Push Docker Image') {
            steps {
                sh "echo ${DOCKER_HUB_PASS} | docker login -u ${DOCKER_HUB_USER} --password-stdin"
                sh "docker push ${IMAGE_NAME}:latest"
            }
        }

        stage('Deploy to Kubernetes') {
            steps { sh "kubectl apply -f deployment.yaml" }
        }
    }
}

