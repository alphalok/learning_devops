def gv
pipeline {
    agent any
    tools {
        maven 'maven-3.9'
    }
    stages {
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
            
        }
        stage("build jar") {
            steps{
                script {
                    echo " testing webHook"
                    gv.buildJar()
                }
            }

        }

        stage("build image") {
            steps{
                script {
                    gv.buildImage()
                }
            }

        }

        stage("deploy"){
            steps{
                script{
                    gv.deployImage()
                }

            }
        }
    }
}
