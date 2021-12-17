package com.framework.strom.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class WordReader extends BaseRichSpout {
    private String inputPath;
    private SpoutOutputCollector collector;

    @Override
    public void open(Map conf, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
        inputPath = (String) conf.get("INPUT_PATH");
    }

    @Override
    public void nextTuple() {
        Collection<File> files = FileUtils.listFiles(new File(inputPath),
                null, true);
        for(File f:files){
            try{
                List<String> lines = FileUtils.readLines(f,"UTF-8");
                for(String line :lines){
                    collector.emit(new Values(line));
                }
//                FileUtils.forceDelete(f);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }
}
