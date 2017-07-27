package com.mall.common.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

//pojo基类，完成2个任务，2个日期，实现序列化
public class BasePojo implements Serializable{
	@Getter
	@Setter
	private Date created;
	@Getter
	@Setter
	private Date updated;
}
