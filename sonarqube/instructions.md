# How to run Sonarqube locally

## Start Sonarqube
Run the command `.\bin\windows-x86-64\StartSonar.bat`  
Currently, the file is in `C:\Users\l.zoggia\Desktop\Programs\sonarqube-9.0.1.46107`

## Generate the Sonarqube project
If it has not already been created, generate the project.  
Most important thing is that it will generate the **token** that you should store in order to reuse later.

## Execute the analysis
From the main folder ogf the project, run the command  
`./gradlew sonarqube -D'sonar.projectKey=pizza' -D'sonar.host.url=http://localhost:9000' -D'<TOKEN>'`  
where `<TOKEN>` is the generated token