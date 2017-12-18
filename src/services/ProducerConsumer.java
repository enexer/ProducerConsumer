package services;

import sample.CallbackInterface;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.MatchResult;


public class ProducerConsumer {

    public static CallbackInterface callback;

    public Producer p_A;
    public Consumer c_A_1;

    public Producer p_B;
    public Consumer c_B_1;

    public Producer p_C;
    public Consumer c_C_1;
    public Consumer c_C_2;

//    Executor exec = Executors.newFixedThreadPool(2);
//    static ExecutorService exec = Executors.newCachedThreadPool();

    private LinkedList<Figure> listA, listB, listC;

    public ProducerConsumer(CallbackInterface callback) {

        this.callback = callback;

        BlockingQueue<Figure> sharedQueueA = new LinkedBlockingQueue<Figure>(Values.bufferSize);
        BlockingQueue<Figure> sharedQueueB = new LinkedBlockingQueue<Figure>(1);
        BlockingQueue<Figure> sharedQueueC = new LinkedBlockingQueue<Figure>(1);


        listA = func_file("A.txt");
        listB = func_file("B.txt");
        listC = func_file("C.txt");

        p_A = new Producer(sharedQueueA, Values.ProducerA, listA);
        c_A_1 = new Consumer(sharedQueueA, Values.ConsumerA1, listA);

        p_B = new Producer(sharedQueueA, Values.ProducerB, listB);
        c_B_1 = new Consumer(sharedQueueA, Values.ConsumerB1, listB);

        p_C = new Producer(sharedQueueA, Values.ProducerC, listC);
        c_C_1 = new Consumer(sharedQueueA, Values.ConsumerC1, listC);
        c_C_2 = new Consumer(sharedQueueA, Values.ConsumerC2, listC);
    }

    public void startThreads(HashSet<Object> hsetObj) {
        HashSet<Object> hset = hsetObj;
        for (Object obj : hset) {
            new Thread((Runnable) obj).start();
            System.out.println("start:" + obj.toString());
        }
    }

    public void setTime(Producer p, int t) {
        p.setTime(t);
    }

    public void setTime(Consumer c, int t) {

        c.setTime(t);
    }

    // File Reader/Writer
    public static LinkedList<Figure> func_file(String file_name) {

        String separator = ",";
        LinkedList<Figure> list = new LinkedList<Figure>();

        File the_file = new File(file_name);

        if (the_file.exists()) {
            System.err.println("FILE ALREADY EXIST: " + file_name);
            ProducerConsumer.callback.updateViewProducer("FILE ALREADY EXIST: " + file_name);
            //System.exit(0);
        } else {
            // create file
            try {
                PrintWriter the_output = new PrintWriter(the_file);
                Random random = new Random();
                for (int i = 0; i < 200; i++) {
                    the_output.println(random.nextInt(100) + separator + random.nextInt(50));
                }
                System.out.println("FINISHED WRITING: " + file_name);
                ProducerConsumer.callback.updateViewProducer("FINISHED WRITING: " + file_name);
                the_output.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        // read data from a file
        try {
            Scanner s = new Scanner(the_file);
            System.out.println("\n########################################\n"+"READING DATA: " + file_name);
            ProducerConsumer.callback.updateViewProducer("READING DATA: " + file_name);
            while (s.hasNext()) {
                String nline=s.nextLine();
                Scanner s2 = new Scanner(nline);
                s2.findInLine("(\\w+)" + separator + "(\\w+)");
                MatchResult result = s2.match();
                list.add(new Figure(Integer.valueOf(result.group(1)), Integer.valueOf(result.group(2))));
                for (int i = 1; i <= result.groupCount(); i++) {
                    System.out.print(result.group(i) + "|");
//                    ProducerConsumer.callback.updateViewProducer(result.group(i) + " ");
                }
                System.out.println("");
//                ProducerConsumer.callback.updateViewProducer("\n");
                ProducerConsumer.callback.updateViewProducer(nline);

            }
            System.out.println("FINISHED READING: " + file_name + "\n########################################");
            ProducerConsumer.callback.updateViewProducer("FINISHED READING: " + file_name);
            s.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return list;
    }

}


