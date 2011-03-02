package org.jprimer.automation;

import java.lang.Runnable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jprimer.validation.config.ValidationConfig;

public class AutomationDaemon {

    public Gene gene;
    public ValidationConfig valConfig;
    public AutomationConfig autoConfig;

    public ThreadPoolExecutor threadPool;

    public Queue<SubSequence> subSequenceQueue = new ConcurrentLinkedQueue<SubSequence>();

    public Queue<SubSequenceValidator> subSeqCandidates = new ConcurrentLinkedQueue<SubSequenceValidator>();

    private Logger log;
    
    public AutomationDaemon(ValidationConfig v, AutomationConfig a) {
        valConfig = v;
        autoConfig = a;

        // Set up thread pool
        this.threadPool = new ThreadPoolExecutor(this.autoConfig.threadPool, this.autoConfig.threadPool, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        log = Logger.getLogger(this.getClass());
        BasicConfigurator.configure();


    }

    public void setGene(String seq) throws Exception {
        this.gene = new Gene(seq, this.autoConfig);
        log.debug("Gene Set");
    }

    public void genereateSubSeqQueue() {
        gene.generateSubSequencePermutations();
        // Copy
        for (SubSequence s : gene.subSeqList) {
            this.subSequenceQueue.offer(s);
            //log.debug("SubSeq Copied : " + s.sequence);
        }
        log.debug(this.subSequenceQueue.size());
    }

    public void validateSubSequences() {
        while (!this.subSequenceQueue.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                //java.util.logging.Logger.getLogger(AutomationDaemon.class.getName()).log(Level.SEVERE, null, ex);
            }
            //SubSequenceValidator ssv = new SubSequenceValidator(this.subSequenceQueue.poll(), this.valConfig, this.autoConfig, this.subSeqCandidates);
            this.threadPool.execute(new SubSequenceValidator(this.subSequenceQueue.poll(), this.valConfig, this.autoConfig, this.subSeqCandidates));
        }
        this.threadPool.shutdown();
        while(!this.threadPool.isTerminated()) {

        }
        log.debug("Executor Finished");
    }

}
