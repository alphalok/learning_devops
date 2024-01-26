#!/usr/bin/env groovy

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

        stage("increment Version"){
            steps{
                script{
                    echo 'incrementing app version'
                    sh  'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit '
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[0][1]
                    env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                }
            }
        }
        stage("build jar") {
            steps{
                script {
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

        stage("commit version update"){
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'github', passwordVariable: 'PASS', usernameVariable: 'USER' )]){
                        sh 'git config --global user.email "jenkins@example.com"'
                        sh 'git config --global user.name "jenkins"'

                        sh 'git status'
                        sh 'git branch'
                        sh 'git config --list'

                        sh "git remote set-url origin https://${USER}:elhou123@@github.com/alphalok/learning_devops.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version bump "'
                        sh 'git push origin HEAD:main'
                    }
                }
            }
        }
    }
}
