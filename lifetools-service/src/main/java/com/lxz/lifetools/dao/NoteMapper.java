package com.lxz.lifetools.dao;

import com.lxz.lifetools.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/****************************************************
 * 创建人：     @author liuxuanzhi    
 * 创建时间: 2021/3/16/16:34
 * 项目名称：  HXAssistant
 * 文件名称: com.lxz.lifetools.dao
 * 文件描述: @Description: ${TODO}
 *
 * All rights Reserved, Designed By 投资交易团队
 * @Copyright:2016-2021
 *
 ********************************************************/
@Mapper
public interface NoteMapper {
    /**
     * @Title: listNoteInfo
     * @Description: 查询所有笔记
     * @param createId
     * @return:    List<node>
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/16/16:48
     */
    List<Note> listNoteInfo(int createId);

    /**
     * @Title: isExist
     * @Description:  判断是否存在该id
     * @param id
     * @return: Note
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/16/16:50
     */
    Note isExist(int id);

    /**
     * @Title: saveNote
     * @Description: 添加笔记
     * @param createId
     * @param updateTime
     * @param title
     * @param content
     * @return:    void
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/16/16:51
     */
    void saveNote(@Param("createId") Integer createId, @Param("updateTime") String updateTime , @Param("title") String title,@Param("content")  String content);

    /**
     * @Title: updateNote
     * @Description: 更新笔记
     * @param id
     * @param updateTime
     * @param title
     * @param content
     * @return:  void
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/16/16:52
     */
    void updateNote(@Param("id") int id, @Param("updateTime") String updateTime,@Param("title") String title,@Param("content") String content);

    /**
     * @Title: deleteNote
     * @Description: 逻辑删除笔记
     * @param id
     * @param createId
     * @return:    void
     * @throws
     * @author: liuxuanzhi
     * @Date:  2021/3/16/16:53
     */
    void deleteNote(@Param("id")int id,@Param("createId")int createId);

    Note getContent(int id);
}
