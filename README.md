工程包含：
1、mybatis源码工程
2、mybatis简单例子工程
3、mybatis集成spring源码
4、mybatis集成spring的例子
5、mybatis分页插件源码

如果需要通过数据库表来反向生成实体类和mapper接口和mapper.xml
1、mybatisNotes/mybatisSimple/src/main/resources/generatorConfig.xml  
2、pom.xml添加：
        <dependency>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-core</artifactId>
            <version>1.3.5</version>
            <scope>test</scope>
        </dependency>
			<plugin>
				<groupId>org.mybatis.generator</groupId>
				<artifactId>mybatis-generator-maven-plugin</artifactId>
				<version>1.3.2</version>
				<configuration>
					<verbose>true</verbose>
					<overwrite>true</overwrite>
				</configuration>
			</plugin>
3、测试类：com.enjoylearning.mybatis.GeneratorConfigTest



分页插件相关资料：
插件理解：https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/Interceptor.md
中文文档:https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md 
使用手册:https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
注意事项:https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/Important.md