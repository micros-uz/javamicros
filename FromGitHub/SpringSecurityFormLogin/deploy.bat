@echo off
del D:\Java\apache-tomcat-7.0.26\webapps\SpringMvcStart.war
rd /s /q D:\Java\apache-tomcat-7.0.26\webapps\SpringMvcStart
copy build\libs\SpringMvcStart.war D:\Java\apache-tomcat-7.0.26\webapps