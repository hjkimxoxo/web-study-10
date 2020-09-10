package web_study_10.service;

import java.util.List;

import web_study_10.dao.TitleDao;
import web_study_10.dao.impl.TitleDaoImpl;
import web_study_10.dto.Title;

public class TitleService {
    private TitleDao titleDao = TitleDaoImpl.getInstance();
    
    public List<Title> showTitles(){
        return titleDao.selectTitleByAll();
    }
    
    public int getNextNo() {
        return titleDao.getNextNo();
    }
    
    public int addTitle(Title title) {
        return titleDao.insertTitle(title);
    }
    
    public Title getTitle(Title title) {
        return titleDao.selectTitleByNo(title);
    }
    
    public int deleteTitle(Title title) {
    	return titleDao.deleteTitle(title);
    }
    
    public int updateTitle(Title title) {
    	return titleDao.updateTitle(title);
    }
}
