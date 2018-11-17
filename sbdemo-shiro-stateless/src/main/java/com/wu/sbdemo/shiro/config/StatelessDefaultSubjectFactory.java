package com.wu.sbdemo.shiro.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * @author: wusq
 * @date: 2018/11/17
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {

    @Override
    public Subject createSubject(SubjectContext context) {

        // 不创建session.
        context.setSessionCreationEnabled(false);
        System.out.println("shiro.config.subjectFactory.createSubject.SessionCreationEnabled.false");
        return super.createSubject(context);

    }

}
