package com.codeworld.fc.system.log.mapper;

import com.codeworld.fc.system.log.entity.Log;
import com.codeworld.fc.system.log.vo.LogSearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LogMapper {

    /**
     * 获取全部日志
     * @return
     */
    List<Log> getAllLog(LogSearchVO logSearchVO);

    /**
     * 保存日志
     * @param log
     */
    void addLog(Log log);

    /**
     * 删除全部日志
     */
    void deleteAllLog();
}
