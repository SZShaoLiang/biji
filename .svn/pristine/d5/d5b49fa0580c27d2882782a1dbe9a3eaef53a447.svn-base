package cn.tedu.note.aop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 记录每个业务方法的执行时间
 * 保存到文件中
 * 
 * 利用了生产者消费者模型，异步的将数据写入到文件中
 *
 */
@Component
@Aspect
public class TimeLogerAspect {
	
	//阻塞队列
	private BlockingQueue<String> queue = new LinkedBlockingDeque<String>(500);
	
	private File file;
	
	private String fileName;
		
	private Thread writer;
	
	@Value("#{config.fileName}")
	public void setFileName(String fileName) {
		this.fileName = fileName;
		file = new File(fileName);
	}
	
	public TimeLogerAspect() {
		writer = new Thread(){
			@Override
			public void run() {
				while(true) {
					try {
						if(queue.isEmpty()) {
							Thread.sleep(500);
							continue;
						}
						//发现队列中有数据
						PrintWriter out = new PrintWriter(new FileOutputStream(file,true));
						while(!queue.isEmpty()) {
							String str = queue.poll();
							out.println(str);
						}
						out.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
					
				}			
			}
		};
		writer.start();
	}
	
	@Around("execution(* cn.tedu.note.service.*Service.*(..))")
	public Object proc(ProceedingJoinPoint joinPoint)throws Throwable {
		long t1 = System.nanoTime();
		Object val = joinPoint.proceed();
		long t2 = System.nanoTime();
		Signature s = joinPoint.getSignature();
		//System.out.println(System.currentTimeMillis()+":"+s+":"+(t2-t1));
		String str = System.currentTimeMillis()+":"+s+":"+(t2-t1);
		//将数据加入队列中
		queue.offer(str);
		return val;
	}
	
}








































