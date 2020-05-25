package com.eye.EyeOfTheStorm.listeners;

import com.eye.EyeOfTheStorm.processors.EntryProcessor;
import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.map.listener.EntryRemovedListener;
import com.hazelcast.map.listener.EntryUpdatedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NewEntryListener implements EntryAddedListener<Long, String>,
        EntryRemovedListener<Long, String>, EntryUpdatedListener<Long, String> {

    private HazelcastInstance hazelcastInstance = Hazelcast.getHazelcastInstanceByName("hazelcast-instance");

    Logger logger = LoggerFactory.getLogger(NewEntryListener.class);
    @Override
    public void entryRemoved(EntryEvent<Long, String> entryEvent) {

    }

    @Override
    public void entryUpdated(EntryEvent<Long, String> entryEvent) {
        logger.info("Modified entry on key " + entryEvent.getKey());
    }

    @Override
    public void entryAdded(EntryEvent entryEvent) {
        logger.info("Modifying entry on key " + entryEvent.getKey() + " old value: " + entryEvent.getValue()
                + " new value: " + entryEvent.getValue() + " Modified!...");
        hazelcastInstance.getMap("testCache").submitToKey(entryEvent.getKey(), new EntryProcessor());
    }
}
