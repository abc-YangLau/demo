package com.framework.strom.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import com.framework.strom.bolt.WordCounter;
import com.framework.strom.bolt.WordSpilter;
import com.framework.strom.spout.WordReader;

public class WordCountTopo {
    public static void main(String[] args) {
        if(args.length!=2){
            System.err.println("USage:inputPath timeOffset");
            System.err.println("such as:java -jar Wordcount.jar D://input/ 2");
            System.exit(2);
        }
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader",new WordReader());
        builder.setBolt("word-spilter",new WordSpilter()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter",new WordCounter()).shuffleGrouping("word-spilter");
        String inputPath = args[0];
        String timeOffset = args[1];
        Config conf = new Config();
        conf.put("INPUT_PATH",inputPath);
        conf.put("TIME_OFFSET",timeOffset);
        conf.setDebug(false);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("kafak-3",conf,builder.createTopology());

    }
}
