# dubbo-demo
a project base on a series of alibaba dubbo ecological  middlewares

1.Prerequisites

Before you begin, install the following:

64bit OS: Linux/Unix/Mac/Windows supported, Linux/Unix/Mac recommended.
64bit JDK 1.8+.
Maven 3.2.x+.

2.Download & Build from Release
There are two ways to get Nacos.

  1)Download source code from Github
      unzip nacos-source.zip
      cd nacos/
      mvn -Prelease-nacos clean install -U  
      cd nacos/distribution/target/nacos-server-0.3.0/nacos/bin
  2)Download run package
      unzip nacos-server-0.3.0.zip  OR tar -xvf nacos-server-0.3.0.tar.gz
      cd nacos/bin
3.Start Server
  Linux/Unix/Mac
  Run the following command to sart(standalone means non-cluster mode):
    sh startup.sh -m standalone
  Windows
  Run the following command to start:
    cmd startup.cmd
  Or double-click the startup.cmd run file.
