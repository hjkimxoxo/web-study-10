package web_study_10.dao;

import java.util.List;

import web_study_10.dto.Title;

public interface TitleDao {
    List<Title> selectTitleByAll();
    
    int getNextNo();
    
    Title selectTitleByNo(Title title);
    
    int insertTitle(Title title);
    
    int deleteTitle(Title title);
    
    int updateTitle(Title title);
}
