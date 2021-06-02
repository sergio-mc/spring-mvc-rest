//package extranet.proyecto.repositories;
//
//import org.springframework.boot.actuate.trace.http.HttpTrace;
//import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Repository;
//
//import java.io.*;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Repository
//class CustomTraceRepository implements HttpTraceRepository {
//
//    AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();
//
//    @Override
//    public List<HttpTrace> findAll() {
//        return Collections.singletonList(lastTrace.get());
//    }
//
//    @Override
//    public void add(HttpTrace trace) {
//        if ("GET".equals(trace.getRequest().getMethod())) {
//            try
//            {
//                String filename= "logFile";
//                FileWriter fw = new FileWriter(filename,true); //the true will append the new data
//                fw.write(
//                        " Request:  " + trace.getRequest().get + "\n" +
//                        " Response:  " + trace.getResponse() + "\n" +
//                        " Principal:  " + trace.getPrincipal() + "\n" +
//                        " Session:  " + trace.getSession() + "\n" +
//                        " TimeStamp:  " + trace.getTimestamp() + "\n" +
//                        " TimeTaken:  " + trace.getTimeTaken() + "\n"
//
//                );
//                fw.close();
//            }
//            catch(IOException ioe)
//            {
//                System.err.println("IOException: " + ioe.getMessage());
//            }
////            lastTrace.set(trace);
//        }
//    }
//
//}
