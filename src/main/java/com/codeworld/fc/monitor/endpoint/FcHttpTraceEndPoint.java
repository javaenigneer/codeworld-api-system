package com.codeworld.fc.monitor.endpoint;

import com.codeworld.fc.common.annotation.FcEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;

import java.util.List;

/**
 * ClassName FcHttpTraceEndPoint
 * Description TODO
 * Author Lenovo
 * Date 2020/10/9
 * Version 1.0
**/
@FcEndPoint
public class FcHttpTraceEndPoint {

    @Autowired(required = false)
    private  RemoteHttpTraceRepository traceRepository;

    public FcHttpTraceEndPoint(RemoteHttpTraceRepository repository) {
        this.traceRepository = repository;
    }

    public FcHttpTraceDescriptor traces() {

        return new FcHttpTraceDescriptor(this.traceRepository.findAll());
    }

    public static final class FcHttpTraceDescriptor {

        private final List<HttpTrace> traces;

        public FcHttpTraceDescriptor(List<HttpTrace> traces) {
            this.traces = traces;
        }

        public List<HttpTrace> getTraces() {

            return this.traces;
        }
    }
}
