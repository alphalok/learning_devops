def buildJar(){
    echo "building the application ..."
    sh 'mvn clean package'
}

def buildImage(){
    echo "building the docker image ..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER' )]){
        sh "docker build -t okooel/learning:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push okooel/learning:${IMAGE_NAME}"

    }
}

def deployImage(){
    echo 'deploying the application...'
}
return this
