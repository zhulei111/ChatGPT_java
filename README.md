# ChatGPT_java
基于selenium调用本地浏览器操作ChatGPT的API
# 作用
Java中只需要调用实例即可自动对ChatGPT的回答进行捕捉并输出
# 效果
```Java
ChatGPT chatGPT=new ChatGPT();
System.out.println(chatGPT.getRe("你是?"));
```
![image](https://user-images.githubusercontent.com/42534870/207804158-ce1ab2cf-c091-4601-8ef4-4c9a08e58f9d.png)

# 使用
1.将src源码文件夹加入项目  
2.启动一个Edge浏览器并且已启用远程调试功能"--remote-debugging-port=9222",端口号不可更改  
3.创建实体 
```Java
ChatGPT chatGPT=new ChatGPT();
```
4.查询实例是否正常工作
```Java
ChatGPT chatGPT=new ChatGPT();
chatGPT.getFlag()//ture为成功
```
5.使用API
```Java
ChatGPT chatGPT=new ChatGPT();
System.out.println(chatGPT.getRe("你的内容"));
```
##PS:
1.浏览器不会自动开启,但在Main提供了示例  
2.在创建示例的时候会自动查询浏览器的所有标签页来自动选择ChatGPT的页面,如果没有会自动打开ChatGPT的页面,可能需要手动完成验证和登录操作  
3.使用``` reload() ```函数可以重新加载页面  
