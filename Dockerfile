# VERSION 0.0.1
# Ĭ��ubuntu server����֧�ְ汾����ǰ��12.04
FROM ubuntu
# ǩ����
MAINTAINER raylew "aishangzoulu@gmail.com"

# ����Դ����װssh server
RUN echo "deb http://archive.ubuntu.com/ubuntu precise main universe"> /etc/apt/sources.list
RUN apt-get update
RUN apt-get install -y openssh-server
RUN mkdir -p /var/run/sshd

# ����raylew sshԶ�̵�¼����Ϊ935478
RUN echo "raylew:935478" | chpasswd 

# ����orache java7Դ��һ���԰�װvim��wget��curl��java7��tomcat7�ȱر�����
RUN apt-get install python-software-properties
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN apt-get install -y vim wget curl oracle-java7-installer tomcat7

# ����JAVA_HOME��������
RUN update-alternatives --display java
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/environment
RUN echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle">> /etc/default/tomcat7

# Add hrms stuff into tomcat server
COPY /hrms /etc/default/tomcat7/tomcat7/webapps/hrms

# ������Ҫ����SSH 22�˿�
EXPOSE 22

# ������Ҫ����Tomcat 8080�˿�
EXPOSE 8080

# ����Tomcat7��ʼ�����У�SSH�ն˷�������Ϊ��̨����
ENTRYPOINT service tomcat7 start && /usr/sbin/sshd -D