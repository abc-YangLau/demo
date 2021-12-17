package com.framework.strom.bolt;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;
import java.util.HashMap;
import java.util.Map;

public class WordCounter extends BaseBasicBolt {
    private Map<String, Integer> counters = new HashMap<String, Integer>();

    @Override
    @SuppressWarnings("rawtype")
    public void prepare(Map stormConf, TopologyContext context) {
        final long timeOffset = Long.parseLong(stormConf.get("TIME_OFFSET").toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Map.Entry<String, Integer> entry : counters.entrySet()) {
                        System.out.println( entry.getKey() + ":" + entry.getValue());
                    }
//                    System.out.println("-------------------------------------");
                    try {
                        Thread.sleep(timeOffset * 1000);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    System.out.println("-------------------------------------");
                }
            }
        }).start();
    }

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String idWltno = tuple.getStringByField("words0");
        Integer amtTr = Integer.parseInt(tuple.getStringByField("words1"));
        if (counters.containsKey(idWltno)) {
            int c = counters.get(idWltno) + amtTr;
            counters.put(idWltno, c);
        } else {
            counters.put(idWltno,1);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
