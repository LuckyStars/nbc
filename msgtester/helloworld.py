# coding: utf-8
import webapp2
import logging


class Main(webapp2.RequestHandler):
    
    def get(self):
        self.post()
        
    def post(self):
        param = self.request.get
        write = self.response.out.write
        logging.info(param)
        logging.info(self.request._body__get())
        self.response.headers['Content-Type'] = 'text/plain'
        write("200 OK")

app = webapp2.WSGIApplication([('/*', Main)])