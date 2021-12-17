package com.framework.strom.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;


public class WordSpilter extends BaseBasicBolt {

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        String line = tuple.getStringByField("line");
        String[] words = line.split(":");
//        for(String word:words){
//            word = word.trim();
//            if(StringUtils.isNotBlank(word)){
//                word = word.toLowerCase();
//                basicOutputCollector.emit(new Values(word));
//            }
//        }
        if(words.length==2){
            basicOutputCollector.emit(new Values(words[0],words[1]));
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("words0","words1"));
    }
}
