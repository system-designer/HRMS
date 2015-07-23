# VERSION 0.0.1
# 默认ubuntu server长期支持版本，当前是12.04
FROM ubuntu
# 签名啦
MAINTAINER raylew "aishangzoulu@gmail.com"

# 更新源，安装ssh server
RUN echo "deb http://archive.ubuntu.com/ubuntu precise main universe"> /etc/apt/sources.list
RUN apt-get update
RUN apt-get install -y openssh-server
RUN mkdir -p /var/run/sshd

# 设置raylew ssh远程登录密码为935478
RUN echo "raylew:935478" | chpasswd 

# 添加orache java7源，一次性安装vim，wget，curl，java7，tomcat7等必备软件
RUN apt-get install python-software-properties
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN apt-get install -y vim wget curl oracle-java7-installer tomcat7

# 设置JAVA_HOME环境变量
RUN update-alternatives --display java
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/environment
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/default/tomcat7

# Add hrms stuff into tomcat server
COPY /hrms /etc/default/tomcat7/tomcat7/webapps/hrms

# 容器需要开放SSH 22端口
EXPOSE 22

# 容器需要开放Tomcat 8080端口
EXPOSE 8080

# 设置Tomcat7初始化运行，SSH终端服务器作为后台运行
ENTRYPOINT service tomcat7 start && /usr/sbin/sshd -D