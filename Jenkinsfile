pipeline {

    agent any

    environment {
        IMAGE_NAME = "vikas0112/mobile-aws-k8s:v1"
    }

    stages {

        stage('Git Clone') {
            steps {
                git 'https://github.com/Vikas0112/mobile-aws-k8s.git'
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                }
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push $IMAGE_NAME'
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                sh 'kubectl apply -f deployment.yaml'
                sh 'kubectl apply -f service.yaml'
            }
        }
    }
}
