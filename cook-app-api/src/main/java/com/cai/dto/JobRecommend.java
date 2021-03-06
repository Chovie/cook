package com.cai.dto;

import lombok.Data;

@Data
public class JobRecommend implements Comparable<JobRecommend>{

    private String recruitId;

    private int isMoreJob;

    private String jobName;

    private String publisherName;

    private String salary;

    private long publishDate;

    private String publisherId;

    private Short recruitType;


    @Override
    public int compareTo(JobRecommend o) {
        if(this.publisherId.equals(o.publisherId)){
            o.jobName=o.jobName+"|"+this.jobName;
            o.isMoreJob = 1;
            return 0;
        }
        //降序
        return (int)(o.publishDate - this.publishDate);
    }

}
