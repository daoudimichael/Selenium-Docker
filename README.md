# docker img selenium

# Introduction

This project runs a smoke test on website: https://bitly.com/ a shortening service by using Java code based Selenium framwork against a remotly standalone Chrome browser using Docker container.

Test steps:
1. Go to https://bitly.com/
2. Shorten a link - www.google.com
3. Verify the result expands to original link

# prerequisites

* Docker
* Git
* Maven
* Java 
* IDE (Eclipse, intellij, netBeans etc)

# Getting started

1. __Clone project to your local machine:__

- clone the project by copy&paste HTTPS/SSH path to your Terminal (get the path from the green right corner button 'Clone or download') - `git clone` + HTTPS/SSH path
- Open the project using IDE (.././keweeAssignment/__keyweeAssignment__)

2. __Preparation of standalone Chrome Docker container:__

__- Verify Docker__

Terminal: `docker --version`
Expected: `Docker version XX.XX.XX-ce, build XXXXX`

__- Pull docker image__

Terminal: `docker pull selenium/standalone-chrome`
Expected: pulling the docker image


__- Start container with Selenium standalone-chrome__

Terminal: `docker run -d -P selenium/standalone-chrome`
Expected (for example): 
`➜  docker git:(master) docker run -d -P selenium/standalone-chrome
3df032f01a346bb0f0922746e92c7d63e65a262e10eb25dfe04452e7fe118c8f
➜  docker git:(master)`

__- Check if container is running__

Terminal: `docker ps`
Expected: new line at the running containers list with:
- IMAGE = "selenium/standalone-chrome"
- PORT (for example) = "0.0.0.0:32768->4444/tcp 

-> ___*********** set the first port (f.e: 32768) to environment.properties file (path:/keyweeAssignment/src/main/resources/environment.properties) in local.port ***********___ <-

-> note: the container can be run on a remote machine, in this case, please set the IP address to ip.address at environment.properties file.


3. __run the test__
