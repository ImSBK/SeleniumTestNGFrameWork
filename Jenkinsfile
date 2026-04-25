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
                    echo "Current Branch: ${env.BRANCH_NAME}"

                    if (env.BRANCH_NAME == 'master' || env.BRANCH_NAME == 'main') {
                        echo "Running FULL suite"
                        bat 'mvn clean test -Dsurefire.suiteXmlFiles=full.xml'

                    } else if (env.BRANCH_NAME.toLowerCase().contains('milestone')) {
                        echo "Running REGRESSION suite"
                        bat 'mvn clean test -Dsurefire.suiteXmlFiles=regression.xml'

                    } else if (env.BRANCH_NAME.toLowerCase().contains('sprint')) {
                        echo "Running SANITY suite"
                        bat 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'

                    } else {
                        echo "Skipping CI for this branch"
                    }
                }
            }
        }
    }
}