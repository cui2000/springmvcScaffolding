package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Language;
import demo.dao.ILanguageDao;

@Repository("languageDao")
public class LanguageDaoImpl extends BaseDaoImpl<Language> implements ILanguageDao {

}