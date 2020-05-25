package com.eye.EyeOfTheStorm.processors;
import com.hazelcast.core.Offloadable;
import com.hazelcast.map.AbstractEntryProcessor;

import java.util.Map;

public class EntryProcessor extends AbstractEntryProcessor<Long, String> implements Offloadable {
    @Override
    public Object process(Map.Entry<Long, String> entry) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value = entry.getValue();
        value += " Modified!";
        entry.setValue(value);
        return null;
    }

    @Override
    public String getExecutorName() {
        return "testExec";
    }
}
