package cn.youai.ssm.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloJob {
	public HelloJob() {
		System.out.println("HelloJob创建成功");
	}
	@Scheduled(cron = "0/1 * *  * * ? ")
	// 每隔1秒隔行一次
	public void run() {
		System.out.println("Hello MyJob  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new Date()));
	}
}
