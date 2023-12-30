#!/usr/bin/env groovy

//@Library('jenkins-shared-library')

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
        [$class : 'GitSCMSource',
        remote:'https://github.com/alphalok/jenkins-shared-library.git',
        credentialsId: 'github'
        ]
)
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
                    buildJar()
                }
            }

        }

        stage("build and push image") {
            steps{
                script {
                    buildImage 'okooel/learning:jma-3.0'
                    dockerLogin()
                    dockerPush 'okooel/learning:jma-3.0'
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
