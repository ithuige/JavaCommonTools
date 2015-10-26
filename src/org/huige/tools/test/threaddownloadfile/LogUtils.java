/**
 * 
 */
package org.huige.tools.test.threaddownloadfile;

/**
 * @author huige
 * 2015年10月25日 下午11:17:53
 *日志工具类
 */
public abstract class LogUtils {
    
    public static void log(Object message) {
        System.err.println(message);
    }
    
    public static void log(String message) {
        System.err.println(message);
    }
    
    public static void log(int message) {
        System.err.println(message);
    }
    
    public static void info(Object message) {
        System.out.println(message);
    }
    
    public static void info(String message) {
        System.out.println(message);
    }
    
    public static void info(int message) {
        System.out.println(message);
    }
}