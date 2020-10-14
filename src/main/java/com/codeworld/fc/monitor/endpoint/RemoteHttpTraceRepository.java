package com.codeworld.fc.monitor.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName RemoteHttpTraceRepository
 * Description TODO
 * Author Lenovo
 * Date 2020/10/9
 * Version 1.0
**/
@Component
public class RemoteHttpTraceRepository implements HttpTraceRepository {


    private int capacity = 100;
    private boolean reverse = true;
    private final List<HttpTrace> traces = new LinkedList();

    public RemoteHttpTraceRepository() {
    }

    public void setReverse(boolean reverse) {
        synchronized(this.traces) {
            this.reverse = reverse;
        }
    }

    public void setCapacity(int capacity) {
        synchronized(this.traces) {
            this.capacity = capacity;
        }
    }

    public List<HttpTrace> findAll() {
        synchronized(this.traces) {
            return Collections.unmodifiableList(new ArrayList(this.traces));
        }
    }

    public void add(HttpTrace trace) {
        synchronized(this.traces) {
            while(this.traces.size() >= this.capacity) {
                this.traces.remove(this.reverse ? this.capacity - 1 : 0);
            }

            if (this.reverse) {
                this.traces.add(0, trace);
            } else {
                this.traces.add(trace);
            }

        }
    }
}
