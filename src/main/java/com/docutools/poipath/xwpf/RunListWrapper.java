package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;

public class RunListWrapper {
    private final List<XWPFRun> runs;

    public RunListWrapper(List<XWPFRun> runs) {
        this.runs = runs;
    }

    public RunWrapper get(int i) {
        return new RunWrapper(runs.get(i));
    }
}
