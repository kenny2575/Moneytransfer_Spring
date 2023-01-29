package ru.netology.moneytransfer.logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Logger {
    private static Logger logger;
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private final static String fileName = "logs" + File.separator + "file.log";
    private Logger(){
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+03"));
    }

    public static Logger getLogger(){
        if (logger == null){
            logger = new Logger();
        }
        return logger;
    }

    public void log(String message){
        byte[] messageByte = new StringBuilder()
                .append("[")
                .append(dateFormat.format(new Date()))
                .append("] - ")
                .append(message)
                .append("\n")
                .toString()
                .getBytes();

        try(FileOutputStream out = new FileOutputStream(fileName, true)){
            out.write(messageByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
