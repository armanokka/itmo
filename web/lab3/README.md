гайд по запуску 3 лабы по вебу
0. Один раз: 

`wildfly/wildfly-26.1.3.Final/bin/jboss-cli.sh --command="module add --name=org.postgresql --resources=/usr/local/share/java/classes/postgresql.jar --dependencies=javax.api,javax.transaction.api"`
   
Все следующие разы:
    
`wildfly/wildfly-26.1.3.Final/bin/jboss-cli.sh`

1. Запускаешь `mvn clean package` в своем проекте. Мавен генерирует war-файл в папке target. Закидываешь его в ~/wildfly/wilfdly-26.1.3.Final/standalone/deployments
2. Запускаешь wildfly-сервер: `bash ~/wildfly/wildfly-26.1.3.Final/bin/standalone.sh`
3. В новом терминале пробрасываешь порты: `ssh -L 29552:localhost:29552 s409703@se.ifmo.ru -p 2222`
4. Смотришь на свою лабу: http://localhost:29552/eclipselink-orm-jsf-demo-1.0-SNAPSHOT/
