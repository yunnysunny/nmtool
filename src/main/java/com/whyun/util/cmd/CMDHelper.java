package com.whyun.util.cmd;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CMDHelper {
    private String response;
    private String errmsg;
    private static final Logger logger = LoggerFactory
		.getLogger(CMDHelper.class);
    private static final boolean IS_WIN
    	= System.getProperty("os.name").toUpperCase().substring(0,3).equals("WIN");

    public String getResponse() {
        return response;
    }

    public String getErrmsg() {
        return errmsg;
    }
    
    public static void main(String[] args) {
        // the command to execute
        CMDHelper cmd = new CMDHelper();
        cmd.execute(new String[]{"sh","-c","/root/bin/svsser -s"});
        cmd.execute(new String[]{"sh","-c","ps -A | grep svsser |wc -l"});
        System.out.println(cmd.getResponse());
        System.out.println(cmd.getErrmsg());
        System.out.println("top");
        cmd.execute("TERM=xterm;top -b -n 1");
        System.out.println(cmd.getResponse());
        System.out.println(cmd.getErrmsg());
    }
    
    public void execute(String cmd) {
    	if (!IS_WIN) {
    		execute(new String[]{"sh", "-c", cmd});
    	} else {
    		execute(new String[]{"cmd","/c",cmd});
    	}        
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public  void execute(String[] cmd) {

//        InputStream pipedOut = null;

        try {

            Process aProcess = Runtime.getRuntime().exec(cmd);

            ExecutorService threadPipe=Executors.newSingleThreadExecutor();            
            ExecutorService threadError=Executors.newSingleThreadExecutor();
            
            Future futurePipe = threadPipe.submit(
                    new CallableSreamGobbber(aProcess.getInputStream()));
            Future futureError = threadError.submit(
                    new CallableSreamGobbber(aProcess.getErrorStream()));
            response = (String) futurePipe.get(3, TimeUnit.SECONDS);
            errmsg = (String)futureError.get(1,TimeUnit.SECONDS);
            aProcess.waitFor();
            threadPipe.shutdown();
            threadError.shutdown();
        } catch (ExecutionException ex) {
            logger.warn( null, ex);
        } catch (TimeoutException ex) {
            logger.warn( "", ex);
        } catch (IOException e) {
            logger.error( "", e);
        } catch (InterruptedException ex) {
            logger.warn( "", ex);
        }
    }
}
@SuppressWarnings("rawtypes")
class CallableSreamGobbber implements Callable {
    private InputStream pipe;
    private static final Logger logger = LoggerFactory
		.getLogger(CallableSreamGobbber.class);
    public CallableSreamGobbber(InputStream pipe) {
        this.pipe = pipe;
    }

    public Object call() {
        String response = null;
        try {
            byte buffer[] = new byte[2048];

            int read = pipe.read(buffer);
            response = new String();
            while (read >= 0) {
                response += new String(buffer,0,read);               
                
                read = pipe.read(buffer);
            }
        } catch (IOException e) {
            logger.warn( "", e);
        } finally {
            if (pipe != null) {
                try {
                    pipe.close();
                } catch (IOException e) {
                }
            }
        }
        return response;
    }
    
}
class StreamGobber implements Runnable {

    private InputStream Pipe;

    public StreamGobber(InputStream pipe) {

        if (pipe == null) {
            throw new NullPointerException("bad pipe");
        }

        Pipe = pipe;
    }

    public void run() {

        try {
            byte buffer[] = new byte[2048];

            int read = Pipe.read(buffer);
            while (read >= 0) {
                System.out.write(buffer, 0, read);
                
                read = Pipe.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Pipe != null) {
                try {
                    Pipe.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
