# ChatGPT_java
基于selenium调用本地浏览器操作ChatGPT的API
## 交流群[692216254](https://jq.qq.com/?_wv=1027&k=ZOdZbW11)
# 作用
Java中只需要调用实例即可自动对ChatGPT的回答进行捕捉并输出
# 效果
```Java
ChatGPT chatGPT=new ChatGPT();
File file=chatGPT.getRe("用java在编写一个抽取1到1000中随机一个数并输出");//file即为回答截图
```
![2XI2I3{7M@DZOE}D Z_NKJH](https://user-images.githubusercontent.com/42534870/208389990-d4e56228-c21a-475f-8496-d6ce20c74e1a.png)


# 使用
1. 将src源码文件夹加入项目  
2. 添加库
```Maven
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>31.1-jre</version>
        </dependency>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.7.1</version>
        </dependency>
```
### 注意:如果使用了springboot需要覆盖默认的坐标
```Maven
<properties>
      <selenium.version>4.7.1</selenium.version>
</properties>
```
3. 启动一个Edge浏览器并且已启用远程调试功能"--remote-debugging-port=9222",端口号需与代码中的保持同步  
   1.关闭 Microsoft Edge 的所有实例  （注意：检查任务管理器中是否残留edge的后台进程，如果有请结束，这是由于edge的启动增强功能导致的）  
   2.使用远程调试端口启动 Microsoft Edge。  
         ```
        msedge.exe --remote-debugging-port=9222
        ```
4. 创建实体 
```Java
ChatGPT chatGPT=new ChatGPT();
```
5. 查询实例是否正常工作
```Java
ChatGPT chatGPT=new ChatGPT();
chatGPT.getFlag()//ture为成功
```
6. 使用API
```Java
ChatGPT chatGPT=new ChatGPT();
File file=chatGPT.getRe("你的内容");
//PS:你可以使用如下代码将File转写为文件 需要引入commons-io包
File outfile = new File("image.png");
try {
    FileUtils.copyFile(file, outfile);
} catch (IOException e) {
    return null;
}
```
## PS:  
1. 浏览器不会自动开启,但在Main提供了示例  
2. 在创建示例的时候会自动查询浏览器的所有标签页来自动选择ChatGPT的页面,如果没有会自动打开ChatGPT的页面,可能需要手动完成验证和登录操作  
3. 使用``` reload() ```函数可以重新加载页面  
