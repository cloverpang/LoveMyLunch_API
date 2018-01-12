package com.lovemylunch.common.beans.dashboard;


public class ChartData {
    private String[] titles;
    private String[] datas;

    public String[] getDatas() {
        return datas;
    }

    public void setDatas(String[] datas) {
        this.datas = datas;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }
}
