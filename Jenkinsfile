pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {
        stage('Run Tests') {
            steps {
                script {
                    if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'main') {
                        echo "Running FULL suite"
                        bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng/full.xml'
                    
                    } else if (env.BRANCH_NAME.contains('release')) {
                        echo "Running REGRESSION suite"
                        bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng/regression.xml'
                    
                    } else {
                        echo "Running SANITY suite"
                        bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng/testng.xml'
                    }
                }
            }
        }
    }
}
