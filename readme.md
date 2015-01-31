# nginx 工具包
这是一个nginx操作的工具集，现在暂时仅仅包含流量分析的功能。
## 1.使用说明
首先需要将src/test/resources下的manage.example.properties文件重命名为manage.properties，然后现在有两项数据需要修改

	#nginx日志存放位置，这里要填绝对路劲
	nginx.log.filename=E:\\mywamp\\nginx-1.6.2\\logs\\access.log
	#nginx程序存放位置，这里是nginx可执行程序的完整路径
	nginx.bin.filename=E:\\mywamp\\nginx-1.6.2\\nginx.exe
### 1.1 流量分析
现在流量分析，是通过分析nginx访问日志的形式进行处理的，所有首先要规定一个访问日志的格式，以和程序的日志分析程序相配合。

具体做法是在`nginx.conf`文件中的`http`代码块内添加一个日志格式声明：

	log_format main '$server_name $status $body_bytes_sent $remote_addr [$time_local] "$request" '
					'"$http_referer" "$http_user_agent" $http_x_forwarded_for';
如果当前nginx只运行了一个server，那么只需在日志格式声明的后面追加一个access_log配置即可：

	access_log  logs/access.log  main;
一般情况下，在一个nginx上是要运行多个server的，一般情况下我们在每个server内部都会配置一个类似文件名为`$server_name.log`的日志文件，以区分不同server的访问记录。但这里我们日志分析的时候，是分析所有server的汇总日志，所以我们在每个server内部追加一个汇总日志，下面我们例子中给出的是`logs/access.log`这个文件。

	server
	{
	    listen 87;
	    server_name www.a.com;
		location / {
			index index.html index.htm index.php;	
			root test_site_a;
		}
		
	    access_log  logs/access.log  main;
	    access_log logs/$server_name.log;
	}
关于流量分析的代码说明，可以参见测试类`com.whyun.nginx.test.TrafficTest`。