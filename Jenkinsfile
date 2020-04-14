pipeline {
  environment {
    registry = "bankwing/demo-api-custinfo"
    registryCredential = 'bankwing-dockerhub'
  }
  agent any
  stages {
      
          
stage ('Pull sourcecode') {
        
    steps{
        script {
          git credentialsId: 'bankwing-gitlab', url: 'https://gitlab.com/poc-aia/demo-api-custinfo'
        }
    }

}

stage ("Build") {
        
    steps{
        script {
    withMaven(
        // Maven installation declared in the Jenkins "Global Tool Configuration"
        maven: 'maven-3.6',
        // Maven settings.xml file defined with the Jenkins Config File Provider Plugin
        // We recommend to define Maven settings.xml globally at the folder level using 
        // navigating to the folder configuration in the section "Pipeline Maven Configuration / Override global Maven configuration"
        // or globally to the entire master navigating to  "Manage Jenkins / Global Tools Configuration"
        mavenSettingsConfig: '') {

      // Run the maven build
      sh "mvn clean verify"

    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe & FindBugs & SpotBugs reports...
    
        }
    }
}

    stage('Building image') {
      steps{
        script {
            
            // This step should not normally be used in your script. Consult the inline help for details.
            withDockerRegistry(credentialsId: 'bankwing-dockerhub', toolName: 'docker-cli') {
                // some block
               def appImage = docker.build(registry + ":$BUILD_NUMBER", "-f ./docker/Dockerfile  --build-arg JARFILE=custinfo-0.0.1-SNAPSHOT.jar .")
                appImage.push()
            }


          
        }
      }
    }
    
    stage('Deploy') {
      steps{
        script {
            def remote = [:]
            remote.name = "node-1"
            remote.host = "10.000.000.1"
            remote.allowAnyHosts = true

            
            withCredentials([sshUserPrivateKey(credentialsId: 'node2-root-key', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                    remote.user = userName
                    remote.identityFile = identity
     
                    sshCommand remote: remote, command: 'podman stop demo-api-custinfo;podman rm demo-api-custinfo;podman run -d --name demo-api-custinfo -p 8081:8080 bankwing/demo-api-custinfo'

                    
            }
            
            remote = [:]
            remote.name = "node-2"
            remote.host = "10.0.0.2"
            remote.allowAnyHosts = true

            
            withCredentials([sshUserPrivateKey(credentialsId: 'node2-root-key', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                    remote.user = userName
                    remote.identityFile = identity
     
                    sshCommand remote: remote, command: 'podman stop demo-api-custinfo;podman rm demo-api-custinfo;podman run -d --name demo-api-custinfo -p 8081:8080 bankwing/demo-api-custinfo'

                    
            }



          
        }
      }
    }
    
    
    
    
  }
}
