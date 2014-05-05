# coding: utf-8
import webapp2
import logging


class Main(webapp2.RequestHandler):
    
    @staticmethod
    def writeParams(req):
        for arg in req.arguments():
            logging.info("param :==> " + str(arg) + ":" + str(req.get(arg)))
        pass
    
    def get(self):
        self.post()
        
    def post(self):
        #param = self.request.get
        write = self.response.out.write
        Main.writeParams(self.request)
        logging.info("request body :==> " + self.request._body__get())
        self.response.headers['Content-Type'] = 'text/plain'
        write("200 OK")

app = webapp2.WSGIApplication([('/*', Main)])