package com.simple.basic.memo.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.MemoVO;

@Mapper
public interface MemoMapper {
	void memoRegist(MemoVO vo);
	List<MemoVO> getMemoList();

}
