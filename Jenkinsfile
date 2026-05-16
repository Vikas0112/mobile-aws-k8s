pipeline {

    agent any

    environment {
        IMAGE_NAME = "ghcr.io/vikas0112/mobile-aws-k8s:v1"
    }

    stages {

        stage('Git Clone') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Vikas0112/mobile-aws-k8s.git'
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

stage('GHCR Login') {
    steps {
        withCredentials([usernamePassword(
            credentialsId: 'aws-k8s-mobile',
            usernameVariable: 'GITHUB_USER',
            passwordVariable: 'GITHUB_TOKEN'
        )]) {

            sh 'echo $GITHUB_TOKEN | docker login ghcr.io -u $GITHUB_USER --password-stdin'
        }
    }
}

        stage('Push Image') {
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
