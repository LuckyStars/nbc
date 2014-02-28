
import org.springframework.context.ApplicationContext;
from org.springframework.web.context import ContextLoader;

class DaoSupport():
    def getSession(self):
        return ContextLoader.getCurrentWebApplicationContext().getBean("sessionFactory").getCurrentSession()

    def getIndex(self,x):
        return  x > 0 and (x-1)*6 or 0
    
    
    def add(self,entity):
        """
        if you put this method in the super class 
        it will not be executed....why?
        """
        print "\n\n\nadd\n\n\n"
        self.getSession().save(entity)