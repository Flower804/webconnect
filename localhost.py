from http.server import HTTPServer, BaseHTTPRequestHandler
import multiprocessing as mp
import time
import os


def body1(self):
    try:
        class Serv(BaseHTTPRequestHandler):

            def do_GET(self):
               if self.path == '/':
                   self.path = '/test.html'
               try:
                   file_to_open = open(self.path[1:]).read()
                   self.send_response(200)
               except:
                   file_to_open = "File not found"
                   self.send_response(404)
               self.end_headers()
               self.wfile.write(bytes(file_to_open, 'utf-8'))

        httpd = HTTPServer(('localhost',8080),Serv)
        print("[body] localhost is running " + '\n')
        httpd.serve_forever() 
    except:
        print("[body] fatal error occored, restarting server" + '\n')
           
def body2(self):
    try:
        class Serv(BaseHTTPRequestHandler):

            def do_GET(self):
               if self.path == '/':
                   self.path = '/Yamisa.html'
               try:
                   file_to_open = open(self.path[1:]).read()
                   self.send_response(200)
               except:
                   file_to_open = "File not found"
                   self.send_response(404)
               self.end_headers()
               self.wfile.write(bytes(file_to_open, 'utf-8'))

        httpd = HTTPServer(('localhost',8000),Serv)
        print("[body] localhost is running " + '\n')
        httpd.serve_forever() 
    except:
        print("[body] fatal error occored, restarting server" + '\n')
        
        
def main():
    
    server_runner = mp.Process(target=body1, args=(10, ))
    second_server_runner = mp.Process(target=body2, args=(10, ))
    
    server_runner.start()
    print("[main] the server as started botting" + '\n')

    second_server_runner.start()
    print("[main] the second server has started botting" + '\n')
    
    while True:
        msg = input('[main] awaiting command, write help for commands' + '\n')
        if msg == "help":
            print("[main] stop - stops the server and the program" + '\n')
        
        if msg == "Stop":
            print("[main] terminating server" + '\n')
            server_runner.terminate()
            time.sleep(0.5)
            print("terminated server" + '\n')
            return 0
        
            
      

if __name__ == "__main__":
    os.system('cls')
    main()

    
    